package tn.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.spring.entity.PDFExporter;
import tn.spring.entity.Question;
 
import tn.spring.repository.QuestionRepository;
 

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date; 
@RestController
public class QuizController {
	  @Autowired
	    private QuestionRepository QR;
	         
	    @GetMapping("api/v1/registartion/users/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws  DocumentException,IOException   {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         System.out.print(currentDateTime);
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Question> listeques = QR.findAll();
	         
	        PDFExporter exporter = new PDFExporter(listeques);
	        
	        exporter.export(response);
	        System.out.print("dddd"); 
	    }
}
