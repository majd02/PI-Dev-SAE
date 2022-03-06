package tn.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.entity.Demande;
import tn.spring.entity.InvitationEvent;
import tn.spring.service.DemandeService;
import tn.spring.service.InvitationService;
@RestController
@RequestMapping(path="api/v1/registartion/invitation")
@AllArgsConstructor
public class InvitaionController {
@Autowired
InvitationService IS;
@Autowired
DemandeService DS;
@PostMapping("/{id}/{ide}")
public void inviter(@RequestBody InvitationEvent ie , @PathVariable("id") Long id, @PathVariable("ide") Long ide,Principal principal) {
	String  email = principal.getName();
    
	IS.inviter(email,ie, id,ide);
}
@GetMapping("/{ide}/{idu}")
public void demande(  Demande d , @PathVariable("ide") Long ide, @PathVariable("idu") Long idu)
{
	DS.demande(d,ide, idu);
}
}
