package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.spring.entity.Article;
import tn.spring.entity.SmsRequest;
import tn.spring.exception.ArticleNotFoundException;
import tn.spring.repository.ArticleRepository;
import tn.spring.repository.SmsSender;

import java.util.List;


@Service
public class ArticleService {

    private final SmsSender smsSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(@Qualifier("twilio") TwilioSmsSender smsSender, JavaMailSender javaMailSender) {
        this.smsSender = smsSender;
        this.javaMailSender = javaMailSender;
    }


    public Article addArticle(Article article){

        sendEmail();
        sendSms(new SmsRequest("+21624049210","test hello world"));
        return articleRepository.save(article);
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }

    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("achrafgasmi58@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

    public List<Article> findAllArticles(){
        return articleRepository.findAll();
    }

    public Article updateArticle(Article article) {
        return  articleRepository.save(article);
    }

    public Article findArticleById(int article_id){
        return articleRepository.findById(article_id).orElseThrow(() -> new ArticleNotFoundException("User by id " + article_id + " was not found"));
    }

    public void deleteArticle(int article_id){
        articleRepository.deleteArticleById(article_id);
    }
}
