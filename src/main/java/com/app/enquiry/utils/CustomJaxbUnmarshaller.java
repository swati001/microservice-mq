package com.app.enquiry.utils;

import com.app.enquiry.model.TestMsg;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

//@XSlf4j
@Component
public class CustomJaxbUnmarshaller {
public void unmarshal(String args) throws Exception{
     try {    

//            JAXBContext jaxbContext = JaxbUtil.getJAXBContext(TestMsg.class);
         StopWatch st = new StopWatch();
         st.start();
         JAXBContext jaxbContext = JAXBContext.newInstance(TestMsg.class);
         st.stop();
         System.out.println("time taken to create instance "+st.getLastTaskTimeMillis());
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         ByteArrayInputStream input = new ByteArrayInputStream(args.getBytes());
         JAXBElement<TestMsg> root = jaxbUnmarshaller.unmarshal(new StreamSource(input), TestMsg.class);
            //TestMsg e=(TestMsg) jaxbUnmarshaller.unmarshal(new StringReader(args));
            System.out.println(root.getValue());
            //e.getId()+" "+e.getName()+" "+e.getSalary());
              
          } catch (JAXBException e) {throw e;}
         
}  
}  
