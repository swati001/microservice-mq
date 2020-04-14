package com.app.enquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
@EnableJms
public class EnquiryApplication {
	
	@Autowired
     	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(EnquiryApplication.class, args);
	}

}
