package com.app.enquiry.utils;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JaxbUtil {
        public static final Map<String, JAXBContext> JAXB_MAP = new HashMap<>();
        public static JAXBContext getJAXBContext(Object object) {
            if(JAXB_MAP.get(object.getClass().getCanonicalName()) != null) {
                return JAXB_MAP.get(object.getClass().getCanonicalName());
            }else {
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
                    JAXB_MAP.put(object.getClass().getCanonicalName(), jaxbContext);
                    return jaxbContext;
                } catch (JAXBException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
