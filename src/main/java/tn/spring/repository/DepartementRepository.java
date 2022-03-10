package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

}