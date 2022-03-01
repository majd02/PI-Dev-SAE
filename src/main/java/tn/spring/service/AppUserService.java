package tn.spring.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.spring.entity.AppUser;
import tn.spring.entity.AppUserRole;
import tn.spring.entity.ConfirmationToken;
import tn.spring.entity.Event;
import tn.spring.repository.EvenRepository;
import tn.spring.repository.UserRepository;
@Service
@Slf4j
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	@Autowired
	UserRepository userRepository ;
	@Autowired
	BCryptPasswordEncoder bcpe ;
	@Autowired
	ConfirmationTokenService cfts ;
	@Autowired
	EvenRepository ER; 

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser appuser =  userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG , email )) );
		var role= Collections.singletonList(new SimpleGrantedAuthority(appuser.getAppUserRole().name()) ) ;
		return new User(appuser.getEmail(), appuser.getPassword(),appuser.isEnabled(),true,true,true,role);
	}
	public String SignUpUser(AppUser appuser) {
		boolean exist = userRepository.findByEmail(appuser.getEmail()).isPresent();
		if (exist) {
			throw new IllegalStateException("email mawjoud ");
		}
		
		String encodedpassword= bcpe.encode(appuser.getPassword());
		appuser.setPassword(encodedpassword);
		appuser.setAppUserRole(AppUserRole.ROLE_CLIENT);
		appuser.setLocked(false);
		userRepository.save(appuser);
		//send confirmation token
		String token =UUID.randomUUID().toString();
		ConfirmationToken confirmationtoken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appuser

				);
		cfts.saveConfirmationToken(confirmationtoken); 
		return token; 
	}

	public int enableAppUser(String email) {
		return userRepository.enableAppUser(email);
	}



	public void favoris(Long idu , Long ide) {
		Event e = ER.findById(ide).orElse(null);
		AppUser u =userRepository.findById(idu).orElse(null);
		u.getEvents().add(e);
		userRepository.save(u);

	}
	public void deletefavoris(Long idu , Long ide) {
		Event e = ER.findById(ide).orElse(null);
		AppUser u =userRepository.findById(idu).orElse(null);
		u.getEvents().remove(e);
		userRepository.save(u);


	}


	/*   public JwtResponse authenticateUser(LoginRequest loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtUtils.generateJwtToken(authentication);

	        AppUser userDetails = (AppUser) authentication.getPrincipal();
	        List roles = userDetails.getAuthorities().stream()
	                .map(item -> item.getAuthority())
	                .collect(Collectors.toList());
	        return new JwtResponse(jwt,
	                userDetails.getId(),
	                userDetails.getUsername(),
	                userDetails.getEmail(),
	                roles);
	    }*/

}
