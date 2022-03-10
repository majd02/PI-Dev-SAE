package tn.spring.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import tn.spring.entity.Collaboration;
import tn.spring.service.CollaborationServiceImpl;


@RestController
@RequestMapping("api/v1/registartion/collaboration")
public class CollaborationController {
@Autowired
CollaborationServiceImpl collaborationService;

//EntrepriseServiceImpl entrepriseService;

//http://localhost:8089/SpringMVCMajd/collaboration/create-collaboration/1
	@PostMapping("/create-collaboration/{id}") 
	public Collaboration createCollaboration(@RequestBody Collaboration c,@PathVariable Long id)
	 {
		
		return collaborationService.createCollaborationAndAssignToEntreprise(c, id);
	}
	//http://localhost:8089/SpringMVCMajd/collaboration/get-all-collaboration
	@GetMapping("/get-all-collaboration")
	public List<Collaboration> getAll(@RequestParam int page, @RequestParam int limit){
		List<Collaboration> listCollaboration = collaborationService.getAllCollaboration();

		return listCollaboration;
	}
	
	
	//http://localhost:8089/SpringMVCMajd/collaboration/get-collaboration-by-id/{collaboration-id}
	@GetMapping("/get-collaboration-by-id/{collaboration-id}")
	public Collaboration getCollaborationById(@PathVariable("collaboration-id") Long id){
		Collaboration c= collaborationService.getCollaborationById(id);
		return c;
	}
	//http://localhost:8089/SpringMVCMajd/collaboration/delete-collaboration/1
	@DeleteMapping("/delete-collaboration/{collaboration-id}")
	public void deleteEntreprise(@PathVariable("complaint-id")Long id) {
		collaborationService.deleteCollaboration(id);
	}
	

	@PutMapping("/update-collaboration/{collaborationId}")
	public Collaboration updateCollaboration(@PathVariable("collaborationId") Long collaborationId){
		Collaboration c = this.collaborationService.getCollaborationById(collaborationId);
		collaborationService.updateCollaboration(collaborationId);
		return c;
	}
	
	
	
}
