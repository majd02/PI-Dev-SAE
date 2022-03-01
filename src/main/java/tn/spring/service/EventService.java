package tn.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entity.Event;
import tn.spring.repository.DemandeRepository;
import tn.spring.repository.EvenRepository;
import tn.spring.repository.UserRepository;

@Service
public class EventService {
	@Autowired
	EvenRepository ER;
	@Autowired
	DemandeRepository DR;
	@Autowired
	UserRepository UR;
public void ajouter(Event e) {
	ER.save(e);
}
public void delete(Long id) {
	Event e =ER.findById(id).orElse(null);
	ER.delete(e);
}
public void deleteAll() {
	ER.deleteAll();
}
public List<Event> Show () {
	List <Event> e=  ER.findAll();
	return e;
}
public void Modifier (Event e,Long id) {
	Event event = ER.findById(id).orElse(null);
	event.setDatedebut(e.getDatedebut());
	event.setDatefin(e.getDatefin());
	event.setName(e.getName());
	event.setLocation(e.getLocation());
	ER.save(event);
}
}
