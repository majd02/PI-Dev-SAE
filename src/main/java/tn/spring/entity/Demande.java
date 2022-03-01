package tn.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
@Data
public class Demande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id ;
	private String etat;
	@ManyToOne
	private AppUser appuser;
	@ManyToOne
	private Event event;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public AppUser getAppuser() {
		return appuser;
	}
	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
