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

    @ManyToOne(cascade=CascadeType.ALL)
    Entreprise entreprise;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="departement")
    public List<AppUser> users ;


}
