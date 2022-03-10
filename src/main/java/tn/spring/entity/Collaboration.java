package tn.spring.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import tn.spring.entity.Entreprise;

@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Collaboration {
	public Long getCollaborationId() {
		return CollaborationId;
	}

	public void setCollaborationId(Long collaborationId) {
		CollaborationId = collaborationId;
	}

	public String getCollaborationTitle() {
		return CollaborationTitle;
	}

	public void setCollaborationTitle(String collaborationTitle) {
		CollaborationTitle = collaborationTitle;
	}

	public Date getCollaborationStartDate() {
		return CollaborationStartDate;
	}

	public void setCollaborationStartDate(Date collaborationStartDate) {
		CollaborationStartDate = collaborationStartDate;
	}

	public Date getCollaborationEndDate() {
		return CollaborationEndDate;
	}

	public void setCollaborationEndDate(Date collaborationEndDate) {
		CollaborationEndDate = collaborationEndDate;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long CollaborationId;
	private String CollaborationTitle;
	private Date CollaborationStartDate;
	private Date CollaborationEndDate;
	
	//@ManyToOne
	//User user;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	Entreprise entreprise;

}
