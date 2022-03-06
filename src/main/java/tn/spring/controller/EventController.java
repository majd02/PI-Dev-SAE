package tn.spring.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
import tn.spring.entity.Event;
import tn.spring.service.AppUserService;
import tn.spring.service.DemandeService;
import tn.spring.service.EventService;

@RestController
@RequestMapping(path="api/v1/registartion/event")
@AllArgsConstructor
public class EventController {
	@Autowired 
	EventService ES;
	@Autowired 
	DemandeService DS;
	@Autowired 
	AppUserService US;
@PostMapping
public void add(@RequestBody Event e)
{
	
	ES.ajouter(e);
}

@PostMapping("/{id}")
public void Edit(@RequestBody Event e, @PathVariable("id") Long id)
{
	ES.Modifier(e, id);
}
@GetMapping
public List<Event> show()
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
