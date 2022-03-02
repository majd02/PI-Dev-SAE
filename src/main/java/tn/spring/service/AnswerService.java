package tn.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entity.Answer;
import tn.spring.entity.AppUser;
import tn.spring.entity.Question;
import tn.spring.entity.Quiz;
import tn.spring.repository.AnswerRepository;
import tn.spring.repository.DemandeRepository;
import tn.spring.repository.QuestionRepository;
import tn.spring.repository.QuizRepository;
import tn.spring.repository.UserRepository;



@Service
public class AnswerService {
	@Autowired
	AnswerRepository AR;
	@Autowired
	QuestionRepository QR;

	@Autowired
	DemandeRepository DR;
	@Autowired
	QuizRepository QuR;

	@Autowired
	UserRepository ur;
  
	
	public Answer ajouter(Long idq ,Answer Answer )
	{

		Question q =  QR.findById(idq).orElse(null);
		Answer.setQuestion(q);
	AR.save(Answer);
	return Answer;
	}
	public void affReponseQuestion (Long idq ,Long Ida)
	{
		Answer a = AR.findById(Ida).orElse(null);
		Question q =  QR.findById(idq).orElse(null);
		q.getAnswers().add(a);
		QR.save(q);
	}
	public void repondre(Long idr, Long idq,String email)
	{
		Answer a = AR.findById(idr).orElse(null);
		a.getContent();
		Question q =  QR.findById(idq).orElse(null);
		Set <Answer> as = q.getAnswers();
		
			 
        if(a.isCorrect()) {Long i= DR.jibid(email);
    	
    	AppUser au = ur.findById(i).orElse(null);
    	int j= au.getPoints();
    		au.setPoints(q.getPoints()+j);
    		ur.save(au);
        }else if(!a.isCorrect()) {
        	System.out.print("noné");
        }
	
	
	
	}
	public void supprimer(Long id)
	{ 
		AR.deleteById(id);
	}
	public List<Answer> Show () {
		List<Answer> list = (List<Answer>)AR.findAll();
		return list;
		}
	public Answer modifier ( Answer answer )
	{
		AR.save(answer);
		return answer;
	}
	
	
}
