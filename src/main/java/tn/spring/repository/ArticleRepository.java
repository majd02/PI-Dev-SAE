package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.entity.Article;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    void deleteArticleById(int article_id);
    Optional<Article> findById(Integer id);
}
