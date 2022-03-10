package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import tn.spring.entity.Collaboration;

@Repository
public interface CollaborationRepository extends JpaRepository <Collaboration, Long>{

}
