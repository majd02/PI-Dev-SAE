package tn.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.entity.Departement;
import tn.spring.entity.Entreprise;
import tn.spring.repository.DepartementRepository;
import tn.spring.repository.EntrepriseRepository;

import java.util.List;

@Service
@Slf4j
public class DepartementServiceImpl implements IDepartementService{


    @Autowired
    EntrepriseRepository entrepriseRepo;
    @Autowired
    DepartementRepository departementRepo;



    @Override
    public Departement createAndAffectDepartementToEntreprise(Departement d, Long id) {
        System.out.print("in method create And Effect departement to Entreprise");
        Entreprise e = entrepriseRepo.findById(id).orElse(null);
        e.getDepartements().add(d);
        d.setEntreprise(e);
        System.out.print("Entreprise :"+id);
        System.out.print("Departement :"+d.toString());

        return departementRepo.save(d);

    }

    @Override
    public List<Departement> getDepartementByEntrepriseId(Long id) {
        Entreprise e = entrepriseRepo.findById(id).orElse(null);
        List<Departement> departmenets = e.getDepartements();
        return departmenets;
    }

    @Override
    public Departement updateDepartement(Departement d) {
        return departementRepo.save(d);
    }

    @Override
    public void deleteDepartement(Long id) {

    	 System.out.print("In Method Delete departement");
    	 System.out.print("Im gonna delete the comment");
        departementRepo.deleteById(id);
        System.out.print("Out Of Method delete departement succefully");



    }
    public Departement getDepartementById(Long id) {
        return departementRepo.findById(id).orElse(null);
    }

}
