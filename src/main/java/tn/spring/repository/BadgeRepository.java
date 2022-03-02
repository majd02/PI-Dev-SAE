package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.*;
import tn.spring.entity.Badge;
@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
