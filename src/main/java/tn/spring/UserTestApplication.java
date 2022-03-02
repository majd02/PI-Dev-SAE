package tn.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class UserTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTestApplication.class, args);
	}

}
