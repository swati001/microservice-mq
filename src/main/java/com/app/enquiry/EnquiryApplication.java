package com.app.enquiry;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
@SpringBootApplication
@EnableJms
public class EnquiryApplication {
	private static Logger log = LoggerFactory.getLogger(EnquiryApplication.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
	   ConfigurableApplicationContext context = SpringApplication.run(EnquiryApplication.class, args);
	   JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
	   log.info("Sending message.");
	   jmsTemplate.convertAndSend("DEV.QUEUE.1", new Email("mymailid@xyz.com", "hi"));
	}

}
