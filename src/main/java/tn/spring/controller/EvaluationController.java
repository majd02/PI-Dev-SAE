package tn.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.spring.entity.Badge;
import tn.spring.entity.Evaluation;
import tn.spring.service.EvaluationService;

@RestController

@RequestMapping(path="api/v1/registartion/admin")
public class EvaluationController {
	@Autowired
	EvaluationService ES;
	@GetMapping("/geteval/{id}")
	public List<Evaluation>get(@PathVariable("id") Long id){
		List<Evaluation>listB=ES.showeq(id);
		return listB;
	}
	@PostMapping("/add/{id}")
	public void aff(@RequestBody Evaluation e , @PathVariable("id") Long id){
		ES.addetaff(e, id);
	}
}
