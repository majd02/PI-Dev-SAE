package tn.spring.service;

import tn.spring.entity.Entreprise;

import java.util.List;

public interface IEntrepriseService {

    public List<Entreprise> getAllEntreprise();
    public Entreprise createEntreprise(Entreprise e);
    public Entreprise updateEntreprise(Long id);
    public Entreprise getEntrepriseById(Long id);
    public void deleteEntreprise(Long id);
}
