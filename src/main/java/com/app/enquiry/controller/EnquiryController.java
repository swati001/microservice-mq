package com.app.enquiry.controller;

import com.app.enquiry.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnquiryController {

@Autowired
private JmsTemplate jmsTemplate;

@Autowired
private EnquiryService enquiryService;

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

@PostMapping(value = "getResourceSalary")
public ResponseEntity<String> calculateSalary(@RequestBody String msg) {
    try {
        String resource = enquiryService.getResource(msg);

        if (resource != null) {
            return new ResponseEntity<String>(resource, HttpStatus.OK);
        }
    }catch(Exception e) {
        return new ResponseEntity<String>("Something went wrong!", HttpStatus.I_AM_A_TEAPOT);
    }
        return new ResponseEntity<String>("Resource not found", HttpStatus.NOT_FOUND);
    }
}
