package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.spring.entity.Subject;
import tn.spring.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService){this.subjectService = subjectService;}

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects () {
        List<Subject> subjects = subjectService.findAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Subject> getSubjectById (@PathVariable("id") int subject_id) {
        Subject subject = subjectService.findSubjectById(subject_id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        Subject newSubject = subjectService.addSubject(subject);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject) {
        Subject updateSubject = subjectService.updateSubject(subject);
        return new ResponseEntity<>(updateSubject, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") int id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
