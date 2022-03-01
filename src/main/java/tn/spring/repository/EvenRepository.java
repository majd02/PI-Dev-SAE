package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Event;

@Repository
public interface EvenRepository extends JpaRepository<Event, Long>{

}
