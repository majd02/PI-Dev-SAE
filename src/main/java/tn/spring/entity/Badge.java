package tn.spring.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name="badge")
public class Badge {
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	private Long id; 
	private String Nom;
	private String description;
	private int nbrePmin;
	private int nbrePmax;
	@OneToMany(mappedBy = "badges")
	private Set<AppUser> appuser;
	public int getNbrePmin() {
		return nbrePmin;
	}
	public void setNbrePmin(int nbrePmin) {
		this.nbrePmin = nbrePmin;
	}
	public int getNbrePmax() {
		return nbrePmax;
	}
	public void setNbrePmax(int nbrePmax) {
		this.nbrePmax = nbrePmax;
	}
	

}
