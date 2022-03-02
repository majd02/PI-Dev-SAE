package tn.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.spring.entity.AppUser;
import tn.spring.entity.Badge;
import tn.spring.repository.BadgeRepository;
import tn.spring.repository.UserRepository;


@Service
public class BadgeService {
	@Autowired
	 BadgeRepository BR;
	@Autowired
	 UserRepository UR;
  
	
	public Badge ajouter(Badge Badge )
	{
	BR.save(Badge);
	return Badge;
	}
	
	public void supprimer(Long id)
	{ 
		BR.deleteById(id);
	}
	public List<Badge> Show () {
		List<Badge> list = (List<Badge>)BR.findAll();
		return list;
		}
	public Badge modifier ( Badge badge )
	{
		BR.save(badge);
		return badge;
	}
	@Scheduled(cron = "*/15 * * * * *" )
	public void cronMethod() {
		List <Badge> bd = BR.findAll();
		List <AppUser> us = UR.findAll();
		for (AppUser u:us)
		{for (Badge b:bd)
		{
			int k = u.getPoints();
			if(b.getNbrePmin() < k && k< b.getNbrePmax()  ) {
				u.setBadges(b);
				UR.save(u);
			}
	 
}
	
	}}
	
	
	
	
}
