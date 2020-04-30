package com.app.enquiry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnquiryController {

@Autowired
private JmsTemplate jmsTemplate;

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
