package com.app.enquiry;

import com.app.enquiry.service.SecretDataService;
import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
@EnableJms
public class EnquiryApplication {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${password}")
	String password;

	@Autowired
	SecretDataService secretDataService;

	public static void main(String[] args) {
//	   ConfigurableApplicationContext context =
			   SpringApplication.run(EnquiryApplication.class, args);
//	   JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
	   log.info("Sending message.");
//	   jmsTemplate.convertAndSend("DEV.QUEUE.1", new Email("mymailid@xyz.com", "hi"));
	}


	@PostConstruct
	private void postConstruct() {
		try {
			log.info("The username is:  " + secretDataService.accessCredentials().getUsername());
			log.info("The password is: " + secretDataService.accessCredentials().getPassword());
		} catch(Exception e) {
			log.error(e.getMessage());
		}
	}

}
