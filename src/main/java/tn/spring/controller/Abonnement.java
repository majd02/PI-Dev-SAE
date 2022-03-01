package tn.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.entity.Demande;
import tn.spring.entity.Event;
import tn.spring.service.AbonnementService;

@RestController
@RequestMapping(path="api/v1/registartion/favoris")
@AllArgsConstructor
public class Abonnement {
	@Autowired
	AbonnementService AS;
	@PostMapping("/follow/{idu}/{ide}")
	public void follow( @PathVariable("ide") Long ide, @PathVariable("idu") Long idu)
	{
		AS.addfavoris(idu, ide);
	}
	@PostMapping("/unfollow/{idu}/{ide}")
	public void unfollow( @PathVariable("ide") Long ide, @PathVariable("idu") Long idu)
	{
		AS.dropfromfavoris(idu, ide);
	}
	

}
