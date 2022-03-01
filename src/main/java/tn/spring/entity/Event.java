
package tn.spring.entity;

import java.sql.Date;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id ;
		private String name ;
		private String location ;
		@Column(name="dateFacture")
		private Date datedebut;
		
		@Column(name="datefin")
		private Date datefin ;
		private int nbplace ;
		@OneToMany(mappedBy = "event")
		private Set<Demande> demandes;
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
