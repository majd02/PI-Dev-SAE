package tn.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.spring.entity.AppUser;
import tn.spring.entity.LoginRequest;
import tn.spring.repository.EvenRepository;
import tn.spring.repository.UserRepository;
import tn.spring.securtity.jwt.JwtProvider;
import tn.spring.util.payload.JwtResponse;
@Service
public class AuthenticationService {
	

	@Autowired
	JwtProvider jwtUtils;
	@Autowired 
	AuthenticationManager authenticationManager ;
	 public JwtResponse authenticateUser(LoginRequest loginRequest) {
	    	
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtUtils.generateToken(authentication);

	        User userDetails = (User) authentication.getPrincipal();
	        List roles = userDetails.getAuthorities().stream()
	                .map(item -> item.getAuthority())
	                .collect(Collectors.toList());
	        return new JwtResponse(jwt,
	                userDetails.getUsername(),
	               
	           
	                roles);
	    }
}
