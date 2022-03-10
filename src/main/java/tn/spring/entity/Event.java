
package tn.spring.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Data
public class Event {
	public int getNbplace() {
		return nbplace;
	}
	public void setNbplace(int nbplace) {
		this.nbplace = nbplace;
	}
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNbplacemin() {
		return nbplacemin;
	}
	public void setNbplacemin(int nbplacemin) {
		this.nbplacemin = nbplacemin;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id ;
		private String name ;
		private String location ;
		@DateTimeFormat(pattern = "dd-mm-yyyy") 
		private Date datedebut;
		private String summary;
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public Set<InvitationEvent> getInvitation() {
			return invitation;
		}
		public void setInvitation(Set<InvitationEvent> invitation) {
			this.invitation = invitation;
		}
		public Set<AppUser> getUsers() {
			return users;
		}
		public void setUsers(Set<AppUser> users) {
			this.users = users;
		}
		@Column(name="datefin")

		@DateTimeFormat(pattern = "dd-mm-yyyy")
		private Date datefin ;
		private int nbplace ;

		private int nbplacemin ;
		@OneToMany(mappedBy = "event")
		private Set<Demande> demandes;
		@OneToMany(mappedBy = "event")
		private Set<InvitationEvent> invitation;
		@ManyToMany(mappedBy="events")
		private Set<AppUser> users;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public Date getDatedebut() {
			return datedebut;
		}
		public void setDatedebut(Date datedebut) {
			this.datedebut = datedebut;
		}
		public Date getDatefin() {
			return datefin;
		}
		public void setDatefin(Date datefin) {
			this.datefin = datefin;
		}
		public Set<Demande> getDemandes() {
			return demandes;
		}
		public void setDemandes(Set<Demande> demandes) {
			this.demandes = demandes;
		}
		

}
