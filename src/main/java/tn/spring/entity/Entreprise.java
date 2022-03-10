package tn.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long EntrepriseId;
    private String FiscalNumber;
    private String Adress;
    private int ContactNumber;
    @JsonIgnore
    private Date CreationDate;
    private int EmployeeNumber;
    @JsonIgnore
    private String Logo;

    @Enumerated(EnumType.STRING)
    private Domaine DomaineEntreprise;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="entreprise")
    @JsonIgnore
    public List<Departement> Departements ;

	public Long getEntrepriseId() {
		return EntrepriseId;
	}

	public void setEntrepriseId(Long entrepriseId) {
		EntrepriseId = entrepriseId;
	}

	public String getFiscalNumber() {
		return FiscalNumber;
	}

	public void setFiscalNumber(String fiscalNumber) {
		FiscalNumber = fiscalNumber;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	public int getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(int contactNumber) {
		ContactNumber = contactNumber;
	}

	public Date getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}

	public int getEmployeeNumber() {
		return EmployeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		EmployeeNumber = employeeNumber;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public Domaine getDomaineEntreprise() {
		return DomaineEntreprise;
	}

	public void setDomaineEntreprise(Domaine domaineEntreprise) {
		DomaineEntreprise = domaineEntreprise;
	}

	public List<Departement> getDepartements() {
		return Departements;
	}

	public void setDepartements(List<Departement> departements) {
		Departements = departements;
	}



    public List<Collaboration> getCollaborations() {
		return collaborations;
	}

	public void setCollaborations(List<Collaboration> collaborations) {
		this.collaborations = collaborations;
	}



	@OneToMany(cascade = CascadeType.ALL, mappedBy="entreprise")

    public List<Collaboration> collaborations ;

}
