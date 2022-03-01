package tn.spring.securtity.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import tn.spring.entity.AppUser;
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
@Autowired
AuthenticationManager authenticationManager;

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
	super();
	this.authenticationManager = authenticationManager;
}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
	String email = request.getParameter("email");
	String password = request.getParameter("password");


	//Log.info("Username is: {}", email); log.info("Password is: {}", password);
	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken (email, password);
	return authenticationManager.authenticate(authenticationToken);

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		AppUser user = (AppUser)authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		String access_token = JWT.create ()
		        .withSubject(user.getUsername ())
		        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
		        .withIssuer (request.getRequestURL ().toString()).sign(algorithm);
		String refresh_token = JWT.create ()
			       .withSubject (user.getUsername ())
			        .withExpiresAt(new Date(System.currentTimeMillis() +  30 * 60 * 1000))
			       .withIssuer (request.getRequestURL ().toString())
			        .sign(algorithm);
	/*	response.setHeader (  "access_token", access_token);
		response.setHeader(  "refresh_token", refresh_token);*/
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put ("refresh_token", refresh_token);
		response.setContentType (MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue (response.getOutputStream (), tokens);
	}

}
