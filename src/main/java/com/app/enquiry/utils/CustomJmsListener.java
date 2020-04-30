package com.app.enquiry.utils;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomJmsListener implements MessageListener {

private static Logger LOG = LoggerFactory.getLogger(CustomJmsListener.class);

@Override
    public void onMessage(Message message)
    {
        LOG.info("CustomJmsListener called");
        
        if (message instanceof TextMessage)
        {
            try
            {
                String msg = ((TextMessage) message).getText();
                LOG.debug("Message contents: {} ", msg);
            }
            catch (Exception e)
            {
                LOG.error("Exception in CustomJmsListener: "+e.getMessage());
               
            }
            
        }
        
    }
}
