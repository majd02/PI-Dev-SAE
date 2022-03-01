package tn.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entity.AppUser;
import tn.spring.entity.Demande;
import tn.spring.entity.Event;
import tn.spring.repository.DemandeRepository;
import tn.spring.repository.EvenRepository;
import tn.spring.repository.UserRepository;

@Service
public class DemandeService {
	@Autowired
	DemandeRepository DR;
	@Autowired
	UserRepository UR;
	@Autowired
	EvenRepository ER;
public void demande(Demande d, Long ide , Long idu )
{
	Event e = ER.findById(ide).orElse(null);
	AppUser u =UR.findById(idu).orElse(null);
    d.setEtat("en attente");
	 d.setAppuser(u); 
	 d.setEvent(e);
	DR.save(d);
}

public void Accept(Long id) {
	
Demande d=	DR.findById(id).orElse(null);
d.setEtat("accepted");
DR.save(d);	
}
public void Refuser(Long id) {
	
	
Demande d=	DR.findById(id).orElse(null);
d.setEtat("rejected");
DR.save(null);
}

public void notgoing(Long ide , Long idu) {

AppUser u =UR.findById(idu).orElse(null);
Set  <Demande> dd= (Set<Demande>) u.getDemandes();
for (Demande dem : dd)
{
	if(dem.getEvent().getId()==ide) {
	DR.delete(dem);
	
}
	
}
}
public List<Demande> Show (String email) {
	Long i= DR.jibid(email);
	
	  List <Demande> e =  DR.dem(i);
	return e;
}
}
