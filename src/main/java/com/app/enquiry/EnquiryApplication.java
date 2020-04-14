package com.app.enquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4J
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
	   jmsTemplate.convertAndSend("mailbox", new Email("mymailid@xyz.com", "hi"));
	}

}
