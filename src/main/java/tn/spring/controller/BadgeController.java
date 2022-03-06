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

import tn.spring.entity.Badge;
import tn.spring.service.BadgeService;
 

@RestController
public class BadgeController {

	@Autowired
	BadgeService BR;
	//http://localhost:8089/SpringMVC/servlet/getbadge
	@GetMapping("/getbadge")
	public List<Badge>get(){
		List<Badge>listB=BR.Show();
		return listB;
	}
	@PostMapping("/addBadge")
	//http://localhost:8089/SpringMVC/servlet/addBadge

	public Badge addBadge(@RequestBody Badge B)
	{
		Badge Badge = BR.ajouter(B);
	return Badge;

	}
	@DeleteMapping("/removeBadge/{id}")
	//http://localhost:8089/SpringMVC/servlet/removeBadge/id

	public void removeBadge(@PathVariable("id") Long id) {
	BR.supprimer(id);
	}
	@PutMapping("/modifierBadge")
	//http://localhost:8089/SpringMVC/servlet/modifierBadge
	public Badge modifierBadge(@RequestBody Badge Badge) {
		return BR.modifier(Badge);
	}
	
}
