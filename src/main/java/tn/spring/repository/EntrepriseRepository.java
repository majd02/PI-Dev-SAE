package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.Entreprise;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long>{

}
