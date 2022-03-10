package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.spring.entity.Departement;
import tn.spring.entity.Entreprise;
import tn.spring.service.DepartementServiceImpl;
import tn.spring.service.EntrepriseServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/entreprise")
public class EntrepriseController {
    @Autowired
    EntrepriseServiceImpl entrepriseService;
    @Autowired
    DepartementServiceImpl departementService;
    //http://localhost:8089/SpringMVCMajd/entreprise/create-entreprise
    @PostMapping("/create-entreprise")
    public Entreprise createEntreprise(@RequestBody Entreprise e)
    {

        return entrepriseService.createEntreprise(e);
    }
    //http://localhost:8089/SpringMVCMajd/entreprise/update-entreprise/{entrepriseId}
    @PutMapping("/update-entreprise/{entrepriseId}")
    public Entreprise updateEntreprise(@PathVariable("entrepriseId") Long entrepriseId){
        Entreprise e = this.entrepriseService.getEntrepriseById(entrepriseId);
        entrepriseService.updateEntreprise(entrepriseId);
        return e;
    }
    //http://localhost:8089/SpringMVCMajd/entreprise/get-all-entreprise
    @GetMapping("/get-all-entreprise")
    public List<Entreprise> getAll(){
        List<Entreprise> listentreprise = entrepriseService.getAllEntreprise();
        return listentreprise;
    }

    //http://localhost:8089/SpringMVCMajd/entreprise/get-entreprise-by-id/{entreprise-id}
    @GetMapping("/get-entreprise-by-id/{entreprise-id}")
    public Entreprise getEntrepriseById(@PathVariable("entreprise-id") Long id){
        Entreprise e= entrepriseService.getEntrepriseById(id);
        return e;
    }
    //http://localhost:8089/SpringMVCMajd/entreprise/delete-entreprise/{entreprise-id}
    @DeleteMapping("/delete-entreprise/{entreprise-id}")
    public void deleteEntreprise(@PathVariable("entreprise-id")Long id) {
        entrepriseService.deleteEntreprise(id);
    }


    
    //////DEPARTMENTS


    // http://localhost:8089/SpringMVCMajd/Entreprise/add-departement/1
    @PostMapping("/add-departement/{idEntreprise}")
    public Departement addDepartement(@RequestBody Departement  d, @PathVariable Long idEntreprise) {

        return departementService.createAndAffectDepartementToEntreprise(d, idEntreprise);

    }

    // http://localhost:8089/SpringMVCMajd/Entreprise/retrieve-departement-by-entreprise/2
    @GetMapping("/retrieve-departement-by-entreprise/{idEntreprise}")
    public List<Departement> retrieveDepartements(@PathVariable("idEntreprise") Long idEntreprise) {
        List<Departement> listDepartement=departementService.getDepartementByEntrepriseId(idEntreprise);
        return listDepartement;
    }

    // http://localhost:8089/SpringMVCMajd/update-departement/2
    @PutMapping("/update-departement/{departement-id}")
    public Departement updateDepartement(@PathVariable("departement-id")Long departementId){
        Departement d = departementService.getDepartementById(departementId);
        departementService.updateDepartement(d);
        return d;
    }

    
    
    
    
}

