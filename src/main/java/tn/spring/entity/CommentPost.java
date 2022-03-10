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


    public Long getCommId() {
		return CommId;
	}

	public void setCommId(Long commId) {
		CommId = commId;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public Date getCommentDate() {
		return CommentDate;
	}

	public void setCommentDate(Date commentDate) {
		CommentDate = commentDate;
	}

	public int getLikes() {
		return Likes;
	}

	public void setLikes(int likes) {
		Likes = likes;
	}

	public int getDislikes() {
		return Dislikes;
	}

	public void setDislikes(int dislikes) {
		Dislikes = dislikes;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Posts getPosts() {
		return posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

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

