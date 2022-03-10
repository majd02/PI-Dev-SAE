package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.entity.LoginRequest;
import tn.spring.service.AppUserService;
import tn.spring.util.payload.JwtResponse;

@RestController

@RequestMapping(path="/admin")
@AllArgsConstructor

public class AdminController {
@Autowired
AppUserService as;
@PostMapping(path="/desable/{id}")
public void enable(@PathVariable("id") Long id)
{

 as.enableUser(id);
}
@PostMapping(path="/makeadmin/{id}")
public void ad(@PathVariable("id") Long id)
{

 as.Admin(id);
}

}
