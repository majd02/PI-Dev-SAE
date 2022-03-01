package tn.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Subject {
    public Subject() {
    }

    public Subject(String name, Set<Article> articles) {
        this.name = name;
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject_id=" + subject_id +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                '}';
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(nullable = false, updatable = false)

    private int subject_id;
    private String name;
    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private Set<Article> articles ;
    @ManyToOne
	private AppUser appuser;

}
