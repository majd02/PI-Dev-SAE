package tn.spring.service;

import java.awt.print.Pageable;
import java.util.List;


import org.springframework.data.domain.Page;


import tn.spring.entity.Collaboration;

public interface ICollaborationService {

	public List<Collaboration> getAllCollaboration();
	public Collaboration getCollaborationById(Long id);
	public Collaboration createCollaborationAndAssignToEntreprise(Collaboration c,Long id);
	public Collaboration updateCollaboration(Long id);
	public void deleteCollaboration(Long id);
}
