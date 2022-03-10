package tn.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor

@Table(name="Posts")
public class Posts {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long postId;

    private String Content;




    @JsonIgnore
    private int NbComment;


    @JsonIgnore
    private int Nbreacts;

    @JsonIgnore

   // @Column(updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
  //  @Temporal(TemporalType.TIMESTAMP)


    private Date DatePost;

    private String ImagePost;

    @JsonIgnore

    private String analysis;

    @JsonIgnore

    private String sentiment;

    @ManyToMany
    @JsonIgnore
    Set<AppUser> userLikes;

    @ManyToMany
    @JsonIgnore
    Set<AppUser> userDislikes;

    @JsonIgnore
    @ManyToOne
    AppUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="posts")
    @JsonIgnore
    public List<CommentPost> comments ;

    @ManyToMany(cascade=CascadeType.ALL)
    @JsonIgnore
    public List<Departement> Departements;


    public Posts(String content, int nbComment, Date datePost, String imagePost, AppUser user) {
        Content = content;
        NbComment = nbComment;
        DatePost = datePost;
        ImagePost = imagePost;
        this.user = user;
    }
}





