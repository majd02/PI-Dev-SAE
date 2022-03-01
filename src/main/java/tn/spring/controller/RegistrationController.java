package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.entity.LoginRequest;
import tn.spring.entity.RegistrationRequest;
import tn.spring.service.AppUserService;
import tn.spring.service.AuthenticationService;
import tn.spring.service.RegistartionService;
import tn.spring.util.payload.JwtResponse;

@RestController
@RequestMapping(path="api/v1/registartion")
@AllArgsConstructor
public class RegistrationController {
	@Autowired 
	RegistartionService RS;
	@Autowired 
	AppUserService US;
@Autowired
PasswordEncoder encoder;
@Autowired
PasswordEncoder authService;
@Autowired
AuthenticationService authenticationService;


@PostMapping
public String signup(@RequestBody RegistrationRequest request)
{
	return RS.register(request);
	
}

@PostMapping(path="login")
public JwtResponse signin(@RequestBody LoginRequest request)
{
	return authenticationService.authenticateUser(request);
}
@GetMapping(path="confirm")
public String confimr(@RequestParam("token") String token) {
	return RS.confirmToken(token);
}


	
}
