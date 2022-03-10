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



    //@OneToMany(cascade = CascadeType.ALL, mappedBy="entreprise")

    //public List<Collaboration> collaborations ;

}
