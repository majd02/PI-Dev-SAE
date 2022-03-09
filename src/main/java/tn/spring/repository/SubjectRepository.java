package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.entity.Subject;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    void deleteSubjectById(int id);
    Optional<Subject> findById(Integer id);
}
