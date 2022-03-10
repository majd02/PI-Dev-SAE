package tn.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DBFile {
	@Id
	
	private Long id ; 
	private String name ; 
	private String type ; 
	@Lob
	private byte[] data ; 
	
	@ManyToOne
	@JsonIgnore
	@Nullable
	private Article article ;
	
	
	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public DBFile( Long id , String name , String type , byte[] data )
	{
		this.id = id ; 
		this.name = name ; 
		this.type = type ; 
		this.data =data ;
	}
}