package tn.spring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;
    private String title;
    private String subject_article;
    private String content;
    @ManyToOne
    private Subject subject;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject_article() {
        return subject_article;
    }

    public void setSubject_article(String subject_article) {
        this.subject_article = subject_article;
    }

    public Article(String title, String subject_article, String content, Subject subject) {
        this.title = title;
        this.subject_article = subject_article;
        this.content = content;
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + id +
                ", title='" + title + '\'' +
                ", subject_article='" + subject_article + '\'' +
                ", content='" + content + '\'' +
                ", subject=" + subject +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article() {
    }


}
