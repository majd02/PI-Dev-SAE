package tn.spring.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name="evaluation")
public class Evaluation {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	private Long id;
	private String content ;
	@ManyToOne
	private AppUser appuser;
	 @Enumerated(EnumType.STRING)
	private Emotion emotion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AppUser getAppuser() {
		return appuser;
	}
	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}
	public Emotion getEmotion() {
		return emotion;
	}
	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

}
