package tn.spring.service;

import tn.spring.entity.Departement;

import java.util.List;


public interface IDepartementService {

    public Departement createAndAffectDepartementToEntreprise(Departement d,Long id);
    public List<Departement> getDepartementByEntrepriseId(Long id);
    public Departement updateDepartement(Departement d);
    public void deleteDepartement(Long id);
}

