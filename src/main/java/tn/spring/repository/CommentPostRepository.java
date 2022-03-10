package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.entity.CommentPost;

@Repository
public interface CommentPostRepository extends JpaRepository<CommentPost, Long> {

}
