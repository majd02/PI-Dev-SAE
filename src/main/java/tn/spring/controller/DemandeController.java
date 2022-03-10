package tn.spring.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.entity.Demande;
import tn.spring.entity.Event;
import tn.spring.entity.RegistrationRequest;
import tn.spring.service.DemandeService;
import tn.spring.service.RegistartionService;

@RestController
@RequestMapping(path="api/v1/registartion/demande")
@AllArgsConstructor
public class DemandeController {
@Autowired 
RegistartionService RS;
@Autowired 
DemandeService DS;
//user y rejoindri
@PostMapping("/{ide}")
public void demande(@RequestBody Demande d , @PathVariable("ide") Long ide, Principal principal)
{
	String email = principal.getName();
	DS.demande(d,ide, email);
}
//userybatel
@PostMapping("/not/{ide}/{idu}")
public void annulerdemande(@PathVariable("ide") Long ide, @PathVariable("idu") Long idu)
{
	DS.notgoing(ide, idu);
}
//admin yaccepti demande
@PostMapping("/accept/{id}")
public void accepter(@PathVariable("id") Long id)
{
	DS.Accept(id);
}

@DeleteMapping("/del/{ide}/{idu}")
public void ss(@PathVariable("ide") Long ide,@PathVariable("idu") Long idu)
{
	DS.notgoing(ide, idu);
}


@RequestMapping(value = "/mes", method = RequestMethod.GET)
@ResponseBody
public List<Demande> show(Principal principal)
{
	String  email = principal.getName();
	 
	//String email = pricipal.getClaimAsString("email");
	return DS.Show(email);
}

}
