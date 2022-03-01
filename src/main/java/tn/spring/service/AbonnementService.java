package tn.spring.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entity.AppUser;
import tn.spring.entity.Demande;
import tn.spring.entity.Event;
import tn.spring.repository.EvenRepository;
import tn.spring.repository.UserRepository;

@Service
public class AbonnementService {
@Autowired
UserRepository UR;
@Autowired 
EvenRepository ER;


public void addfavoris(Long idu,Long ide) {
	AppUser u =UR.findById(idu).orElse(null);
	Event e =ER.findById(ide).orElse(null);
	u.getEvents().add(e);
	UR.save(u);
}
public void dropfromfavoris(Long idu,Long ide) {
	AppUser u =UR.findById(idu).orElse(null);
	Event e =ER.findById(ide).orElse(null);
	u.getEvents().remove(e);
	UR.save(u);
	
}

}
