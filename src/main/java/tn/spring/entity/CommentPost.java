package tn.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CommentPost {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long CommId;


    @NonNull
    private String Comment;
    @JsonIgnore
    private Date CommentDate;
    @JsonIgnore
    private int Likes;
    @JsonIgnore
    private int Dislikes;

     @ManyToOne
    AppUser user;

    @ManyToOne(cascade=CascadeType.ALL)
    Posts posts;

    public CommentPost(String comment, int likes, int dislikes) {
        super();
        Comment = comment;
        Likes = likes;
        Dislikes = dislikes;
    }
}

