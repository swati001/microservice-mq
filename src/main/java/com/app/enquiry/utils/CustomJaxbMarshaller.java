import java.io.FileOutputStream;  
  
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.Marshaller;  
  
  
public class CustomJaxbMarshaller {  
public void marshalObj(String[] args) throws Exception{  
    JAXBContext contextObj = JAXBContext.newInstance(Test.class);  
  
    Marshaller marshallerObj = contextObj.createMarshaller();  
    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
  
    Test test=new Test(1,"Swati",1000);  
      
    marshallerObj.marshal(test, new FileOutputStream("Test.xml"));  
       
}  
}  
