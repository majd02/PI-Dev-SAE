package tn.spring.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.spring.entity.Question;
import tn.spring.service.QuestionService;
 

@RestController

public class QuestionController {
	@Autowired
	QuestionService QR;
	//http://localhost:8089/SpringMVC/servlet/getquestion
	@GetMapping("/getquestion")
	public List<Question>getQ(){
		List<Question>listQ=QR.Show();
		return listQ;
	}
	@PostMapping("/addQuestion")
	//http://localhost:8089/SpringMVC/servlet/addQuestion

	public Question addQuestion(@RequestBody Question Q)
	{
		Question Question = QR.ajouter(Q);
	return Question;

	}
	@DeleteMapping("/removeQuestion/{id}")
	//http://localhost:8089/SpringMVC/servlet/removeQuestion/id

	public void removeQuestion(@PathVariable("id") Long id) {
	QR.supprimer(id);
	}
	
	@PutMapping("/modifierQuestion")
	//http://localhost:8089/SpringMVC/servlet/modifierQuestion
	public Question modifierQuestion(@RequestBody Question Question) {
		return QR.modifier(Question);
	}

}
