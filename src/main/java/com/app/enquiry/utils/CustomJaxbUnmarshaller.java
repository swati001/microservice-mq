import java.io.File;  
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.JAXBException;  
import javax.xml.bind.Unmarshaller;  
  
public class XMLToObject {  
public void unmarshal(String[] args) {  
     try {    
            File file = new File("test.xml");    
            JAXBContext jaxbContext = JAXBContext.newInstance(Test.class);    
         
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();    
            Test e=(Test) jaxbUnmarshaller.unmarshal(file);    
            System.out.println(e.getId()+" "+e.getName()+" "+e.getSalary());  
              
          } catch (JAXBException e) {e.printStackTrace(); }    
         
}  
}  
