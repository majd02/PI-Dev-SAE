package tn.spring.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.spring.entity.*;
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class AppUser implements UserDetails{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String firstname ;
	private String lastname ;
	private String email ;
	private String password ;
	private int points;
	@OneToOne(mappedBy = "appuser")
	 
	private InvitationEvent invitationev;



	public InvitationEvent getInvitationev() {
		return invitationev;
	}






	public void setInvitationev(InvitationEvent invitationev) {
		this.invitationev = invitationev;
	}






	public int getWarningNum() {
		return warningNum;
	}






	public void setWarningNum(int warningNum) {
		this.warningNum = warningNum;
	}






	public Departement getDepartement() {
		return departement;
	}






	public void setDepartement(Departement departement) {
		this.departement = departement;
	}






	public List<Posts> getPosts() {
		return posts;
	}






	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}






	public List<CommentPost> getUserComments() {
		return userComments;
	}






	public void setUserComments(List<CommentPost> userComments) {
		this.userComments = userComments;
	}

	private int warningNum;

	@ManyToOne(cascade= CascadeType.ALL)
	@JsonIgnore
	public Departement departement;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnore
	public List<Posts> posts;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	@JsonIgnore
	public List<CommentPost> userComments;






@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	public String getFirstname() {
	return firstname;
}






public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public Boolean getLocked() {
	return locked;
}

public void setLocked(Boolean locked) {
	this.locked = locked;
}

public Set<Quiz> getQuizs() {
	return quizs;
}

public void setQuizs(Set<Quiz> quizs) {
	this.quizs = quizs;
}

public Set<Subject> getSubjects() {
	return subjects;
}

public void setSubjects(Set<Subject> subjects) {
	this.subjects = subjects;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

	private Boolean locked ;
	private Boolean enabled;
	@OneToMany(mappedBy = "appuser")
	private Set<Demande> demandes;
	@ManyToMany
	private Set<Quiz> quizs;
	@ManyToMany 
	private Set<Event> events;
	@OneToMany(mappedBy = "appuser")
	private Set<Subject> subjects;
	@ManyToOne
	private  Badge badges;
	
	public AppUser() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AppUser(String firstname, String lastname, String email, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		
		return Collections.singletonList(authority);
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Badge getBadges() {
		return badges;
	}

	public void setBadges(Badge badges) {
		this.badges = badges;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(Set<Demande> demandes) {
		this.demandes = demandes;
	}

	

	

	public AppUserRole getAppUserRole() {
		return appUserRole;
	}

	public void setAppUserRole(AppUserRole appUserRole) {
		this.appUserRole = appUserRole;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	
	

}
