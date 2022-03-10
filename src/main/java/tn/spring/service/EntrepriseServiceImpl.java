package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.entity.Entreprise;
import tn.spring.repository.EntrepriseRepository;

import java.util.List;


@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
    @Autowired
    EntrepriseRepository entrepriseRepo;

    @Override
    public List<Entreprise> getAllEntreprise() {

        return entrepriseRepo.findAll();
    }

    @Override
    public Entreprise createEntreprise(Entreprise e) {

        return entrepriseRepo.save(e);
    }

    @Override
    public Entreprise updateEntreprise(Long id) {
        Entreprise e = entrepriseRepo.findById(id).orElse(null);
        entrepriseRepo.save(e);
        return e;
    }

    @Override
    public Entreprise getEntrepriseById(Long id) {

        return entrepriseRepo.findById(id).get();
    }

    @Override
    public void deleteEntreprise(Long id) {

        entrepriseRepo.deleteById(id);
    }

}

