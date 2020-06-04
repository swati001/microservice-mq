package com.app.enquiry.utils;

import com.app.enquiry.model.TestMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
  
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.Marshaller;  
  
@Component
public class CustomJaxbMarshaller {
    @Autowired
    public JaxbUtil jaxbCtx;

public void marshalObj(Object obj) throws Exception{

    JAXBContext contextObj = JaxbUtil.getJAXBContext(TestMsg.class);
  
    Marshaller marshallerObj = contextObj.createMarshaller();  
    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    TestMsg test=(TestMsg)obj;
      
    marshallerObj.marshal(test, new FileOutputStream("TestMsg.xml"));
       
}  
}  
