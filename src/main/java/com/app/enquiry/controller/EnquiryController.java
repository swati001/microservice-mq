package com.app.enquiry.controller;

@RestController
public class EnquiryController {

@Autowired
Private JmsTemplate jmsTemplate;

@GetMapping("send")
public String send(){
    try{
        jmsTemplate.convertAndSend("DEV.QUEUE.1", "test");
        return "SUCCESS";
    }catch(JmsException ex){
        ex.printStackTrace();
        return "FAIL";
    }
}
@GetMapping("receive")
public String receive(){
    try{
        return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
    }catch(JmsException ex){
        ex.printStackTrace();
        return "FAIL";
    }
}
}
