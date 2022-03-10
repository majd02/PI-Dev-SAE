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

    public Long getPostId() {
		return postId;
	}


	public void setPostId(Long postId) {
		this.postId = postId;
	}


	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}


	public int getNbComment() {
		return NbComment;
	}


	public void setNbComment(int nbComment) {
		NbComment = nbComment;
	}


	public int getNbreacts() {
		return Nbreacts;
	}


	public void setNbreacts(int nbreacts) {
		Nbreacts = nbreacts;
	}


	public Date getDatePost() {
		return DatePost;
	}


	public void setDatePost(Date datePost) {
		DatePost = datePost;
	}


	public String getImagePost() {
		return ImagePost;
	}


	public void setImagePost(String imagePost) {
		ImagePost = imagePost;
	}


	public String getAnalysis() {
		return analysis;
	}


	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}


	public String getSentiment() {
		return sentiment;
	}


	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}


	public Set<AppUser> getUserLikes() {
		return userLikes;
	}


	public void setUserLikes(Set<AppUser> userLikes) {
		this.userLikes = userLikes;
	}


	public Set<AppUser> getUserDislikes() {
		return userDislikes;
	}


	public void setUserDislikes(Set<AppUser> userDislikes) {
		this.userDislikes = userDislikes;
	}


	public AppUser getUser() {
		return user;
	}


	public void setUser(AppUser user) {
		this.user = user;
	}


	public List<CommentPost> getComments() {
		return comments;
	}


	public void setComments(List<CommentPost> comments) {
		this.comments = comments;
	}


	public List<Departement> getDepartements() {
		return Departements;
	}


	public void setDepartements(List<Departement> departements) {
		Departements = departements;
	}


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





