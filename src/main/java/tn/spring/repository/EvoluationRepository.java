package tn.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Evaluation;
@Repository
public interface EvoluationRepository extends JpaRepository<Evaluation,Long>{
	@Query(value =" Select * from evaluation  where appuser_id_user=:id", nativeQuery = true)
	public List<Evaluation> jibid(@Param("id") Long id);
}
