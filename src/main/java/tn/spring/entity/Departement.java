package tn.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Departement {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long DepartementId;
    private String DepartmentName;

    public Long getDepartementId() {
		return DepartementId;
	}

	public void setDepartementId(Long departementId) {
		DepartementId = departementId;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<AppUser> getUsers() {
		return users;
	}

	public void setUsers(List<AppUser> users) {
		this.users = users;
	}

	@ManyToOne(cascade=CascadeType.ALL)
    Entreprise entreprise;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="departement")
    public List<AppUser> users ;


}
