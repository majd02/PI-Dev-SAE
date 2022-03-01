package tn.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande,Long>{
	@Query(value =" Select id from app_user  where email=:email", nativeQuery = true)
	public Long jibid(@Param("email") String email);

@Query(value =" Select etat , appuser_id , event_id  from demande  where appuser_id=:id", nativeQuery = true)
public List<Demande> dem(@Param("id") Long id);
}
