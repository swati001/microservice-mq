package com.app.enquiry.utils;

import com.app.enquiry.model.TestMsg;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;  
import javax.xml.bind.Unmarshaller;  

//@XSlf4j
@Component
public class CustomJaxbUnmarshaller {
public void unmarshal(String args) throws Exception{
     try {    
            //File file = new File("Test.xml");
            JAXBContext jaxbContext = JaxbUtil.getJAXBContext(TestMsg.class);
         
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();    
            TestMsg e=(TestMsg) jaxbUnmarshaller.unmarshal(new StringReader(args));
            System.out.println(e.getId()+" "+e.getName()+" "+e.getSalary());  
              
          } catch (JAXBException e) {throw e;}
         
}  
}  
