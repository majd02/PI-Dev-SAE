package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Quiz;
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{

}
