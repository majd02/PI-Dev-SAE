package tn.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.entity.Subject;
import tn.spring.exception.SubjectNotFoundException;
import tn.spring.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addSubject(Subject subject){
        return subjectRepository.save(subject);
    }
    public List<Subject> findAllSubjects(){
        return subjectRepository.findAll();
    }

    public Subject updateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject findSubjectById(int id){
        return subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException("Subject by id " + id + " was not found"));
    }

    public void deleteSubject(int id){
        subjectRepository.deleteSubjectById(id);
    }
}
