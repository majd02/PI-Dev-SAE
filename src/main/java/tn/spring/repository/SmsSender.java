package tn.spring.repository;

import tn.spring.entity.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
