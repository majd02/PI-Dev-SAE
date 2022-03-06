package tn.spring.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter 
public class InvitationEvent {
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
private String Status ;
@OneToOne
private AppUser appuser;
@ManyToOne
private Event event;
public Event getEvent() {
	return event;
}
public void setEvent(Event event) {
	this.event = event;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
public AppUser getAppuser() {
	return appuser;
}
public void setAppuser(AppUser appuser) {
	this.appuser = appuser;
}
public static long getSerialversionuid() {
	return serialVersionUID;
} 
}
