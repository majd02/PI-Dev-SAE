package tn.spring.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


import io.jsonwebtoken.io.IOException;
import tn.spring.entity.AppUser;
import tn.spring.service.AppUserService;

@RestController
public class UserCOntroller {
 
    @Autowired
    private AppUserService service;
     
     
    @GetMapping("export")
    public void exportToCSV(HttpServletResponse response) throws IOException, java.io.IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<AppUser> listUsers = service.dddd();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"User ID", "E-mail", "Full Name", "Roles", "Enabled"};
        String[] nameMapping = {"id", "email", "points", "appUserRole", "enabled"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (AppUser user : listUsers) {
            csvWriter.write(user, nameMapping);
        }
         
        csvWriter.close();
         
    }
}
    