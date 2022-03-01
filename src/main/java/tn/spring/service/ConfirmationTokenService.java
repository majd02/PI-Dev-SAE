package tn.spring.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.spring.entity.ConfirmationToken;
import tn.spring.repository.ConfirmationTokenRepository;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
@Autowired
ConfirmationTokenRepository CTR;
public void saveConfirmationToken(ConfirmationToken token)
{ 
	CTR.save(token);
	
}
public Optional<ConfirmationToken> getToken(String token) {
    return CTR.findByToken(token);
}
public int setConfirmedAt(String token) {
    return CTR.updateConfirmedAt(
            token, LocalDateTime.now());
}
}
