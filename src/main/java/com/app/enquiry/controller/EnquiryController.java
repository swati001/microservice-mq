package com.app.enquiry.controller;

@RestController
public class EnquiryController {

@Autowired
Private JmsTemplate jmsTemplate;

@GetMapping(“/send”)
public String sendMessage(String msg){
   
}
}
