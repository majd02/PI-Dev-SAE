package tn.spring.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.spring.entity.Answer;
import tn.spring.service.AnswerService;

@RequestMapping(path="api/v1/registartion/answer")
@RestController
public class AnswerController {

	@Autowired
	AnswerService AR;
	//http://localhost:8089/SpringMVC/servlet/getanswer
	@GetMapping("/getanswer")
	public List<Answer>getA(){
		List<Answer>listA=AR.Show();
		return listA;
	}
	@PostMapping("/addAnswer/{idq}")
	//http://localhost:8089/SpringMVC/servlet/addAnswer

	public Answer addAnswer(@PathVariable("idq") Long idq,@RequestBody Answer A)
	{
		Answer Answer = AR.ajouter(idq ,A);
	return Answer;

	} 
	@PostMapping("/ver/{idr}/{idq}")
	//http://localhost:8089/SpringMVC/servlet/removeAnswer/id

	public void verif(@PathVariable("idr") Long idr,@PathVariable("idq") Long idq,Principal principal) {

		String  email = principal.getName();
		AR.repondre(idr, idq,email);
	}
	
	
	@DeleteMapping("/removeAnswer/{id}")
	//http://localhost:8089/SpringMVC/servlet/removeAnswer/id

	public void removeAnswer(@PathVariable("id") Long id) {
	AR.supprimer(id);
	}
	@PutMapping("/modifierAnswer")
	//http://localhost:8089/SpringMVC/servlet/modifierAnswer
	public Answer modifierAnswer(@RequestBody Answer Answer) {
		return AR.modifier(Answer);
	}
	
}
