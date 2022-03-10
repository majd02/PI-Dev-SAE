package tn.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import tn.spring.entity.AppUser;
import tn.spring.entity.Emotion;
import tn.spring.entity.Evaluation;
import tn.spring.repository.EvoluationRepository;
import tn.spring.repository.UserRepository;

@Service
public class EvaluationService {
	@Autowired
	EvoluationRepository ER;
	@Autowired
	UserRepository UR;
	@Autowired
	EmailService ES; 
public void add(Evaluation e) {
	ER.save(e);
}
public void addetaff(Evaluation e,Long id) {
	AppUser u=UR.findById(id).orElse(null);
	e.setAppuser(u);
	ER.save(e);
	
}
public List<Evaluation> show () {
	return ER.findAll();
}
public  List<Evaluation> showeq(Long id){
return ER.jibid(id);
}
}
