package tn.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.entity.Article;
import tn.spring.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles () {
        List<Article> articles = articleService.findAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/find/{article_id}")
    public ResponseEntity<Article> getArticleById (@PathVariable("article_id") int article_id) {
        Article article = articleService.findArticleById(article_id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article newArticle = articleService.addArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        Article updateArticle = articleService.updateArticle(article);
        return new ResponseEntity<>(updateArticle, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{article_id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("article_id") int article_id) {
        articleService.deleteArticle(article_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

