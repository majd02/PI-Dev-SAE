package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.*;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
