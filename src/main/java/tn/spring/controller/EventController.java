package tn.spring.controller;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.entity.Demande; 
import tn.spring.repository.EvenRepository;
import tn.spring.service.AppUserService;
import tn.spring.service.DemandeService;
import tn.spring.service.EventService;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import org.apache.commons.logging.Log;


@RestController
@RequestMapping(path="api/v1/registartion/event")
@AllArgsConstructor
public class EventController {
	@Autowired 
	EventService ES;
	@Autowired 
	EvenRepository ER;
	@Autowired 
	DemandeService DS;
	@Autowired 
	AppUserService US;
	private final static Log logger = LogFactory.getLog(EventController.class);
    private static final String APPLICATION_NAME = "super5";
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static com.google.api.services.calendar.Calendar client;

    File keyFile=new File("src/main/resources/auth123.p12");
    GoogleClientSecrets clientSecrets;
    GoogleAuthorizationCodeFlow flow;
    Credential credential;

    HttpTransport TRANSPORT ;
    private String SERVICE_ACCOUNT="super5@monprojet-343322.iam.gserviceaccount.com";





    private Set<Event> events = new HashSet();



    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @GetMapping("/add")
    public ResponseEntity<String> oauth2Callback(HttpServletRequest request) throws IOException, GeneralSecurityException {
        com.google.api.services.calendar.model.Events eventList;
        String message;
        List<tn.spring.entity.Event> evs = ER.findAll();
        for(tn.spring.entity.Event ev : evs) {
        		// @formatter:off
 
// @formatter:on
;

//           TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
//           credential = flow.createAndStoreCredential(response, "userID"); //for Oauth2
        Preconditions.checkArgument( !Strings.isNullOrEmpty(APPLICATION_NAME ),
                "applicationName cannot be null or empty!" );
        try {
            TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();


            credential = new GoogleCredential.Builder().setTransport(TRANSPORT)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(SERVICE_ACCOUNT)
                    .setServiceAccountScopes(Collections.singleton(CalendarScopes.CALENDAR))
                    .setServiceAccountPrivateKeyFromP12File(keyFile)

                    .build();
            credential.refreshToken();
            client = new com.google.api.services.calendar.Calendar.Builder(TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();
            System.out.println(client);
            Events events = client.events();
            Event event = new Event()
                    .setSummary(ev.getSummary())
                    .setLocation(ev.getLocation())
                    .setDescription(ev.getDescription());

            DateTime startDateTime = new DateTime(ev.getDatedebut());
            EventDateTime start = new EventDateTime()

                    .setDateTime(startDateTime)
                    .setTimeZone("(GMT+01:00) Central European Standard Time - Tunis");
            event.setStart(start);

         //   DateTime endDateTime = new DateTime("2022-08-01T17:00:00-07:00");
            DateTime endDateTime = new DateTime(ev.getDatefin());
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("America/Los_Angeles");
            eventList = events.list("primary").setTimeMin(startDateTime).setTimeMax(endDateTime).execute();
            for (Demande dm :ev.getDemandes()) {
            message = eventList.getItems().toString();



            event.setEnd(end);
/*String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
            event.setRecurrence(Arrays.asList(recurrence));
         EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("nidhal.haboubi@esprit.tn"),
                new EventAttendee().setEmail("samer.bader@esprit.tn"),
            };
      event.setAttendees(Arrays.asList(attendees));*/

            EventReminder[] reminderOverrides = new EventReminder[] {
                    new EventReminder().setMethod("email").setMinutes(24 * 60),
                    new EventReminder().setMethod("popup").setMinutes(10),
            };
            Event.Reminders reminders = new Event.Reminders()
                    .setUseDefault(false)
                    .setOverrides(Arrays.asList(reminderOverrides));
            event.setReminders(reminders);

            String calendarId = "primary";
           
            	
            	System.out.print(dm.getAppuser().getEmail());
            event = client.events().insert(dm.getAppuser().getEmail(), event).execute();
            
            System.out.printf("Event created: %s\n", event.getHtmlLink());


            System.out.println("cal message:" + message);
            return new ResponseEntity<>(message, HttpStatus.OK);}
        }catch(IOException e)
        {
            System.out.println(e);
        }}
        return new ResponseEntity<>("Nothing", HttpStatus.OK);
    }

    public Set<Event> getEvents() throws IOException {
        return this.events;
    }
@PostMapping
public void add(@RequestBody tn.spring.entity.Event e)
{
	
	ES.ajouter(e);
}

@PostMapping("/{id}")
public void Edit(@RequestBody tn.spring.entity.Event e, @PathVariable("id") Long id)
{
	ES.Modifier(e, id);
}
@GetMapping
public List<tn.spring.entity.Event> show()
{
	
	 
	//String email = pricipal.getClaimAsString("email");
	return ES.Show();
}
@DeleteMapping("/{id}")
public void delete(@PathVariable("id") Long id)
{
	ES.delete(id);
}
@PostMapping("/{idu}/{ide}")
public void delete(@PathVariable("idu") Long idu,@PathVariable("ide") Long ide)
{
	US.deletefavoris(idu, ide);
}


}
