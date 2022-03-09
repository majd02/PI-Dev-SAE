package tn.spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.spring.entity.SmsRequest;
import tn.spring.service.ArticleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {

    private final ArticleService articleService;

    public SmsController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest){
        articleService.sendSms(smsRequest);
    }
}
