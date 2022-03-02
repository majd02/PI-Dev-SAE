package tn.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entity.Question;
import tn.spring.repository.QuestionRepository;


@Service
public class QuestionService {
	@Autowired
	QuestionRepository QR;
  
	
	public Question ajouter(Question Question )
	{
	QR.save(Question);
	return Question;
	}
	
	public void supprimer(Long id)
	{ 
		QR.deleteById(id);
	}
	public List<Question> Show () {
		List<Question> list = (List<Question>)QR.findAll();
		return list;
		}
	public Question modifier ( Question question )
	{
		QR.save(question);
		return question;
	}
	
	
}
