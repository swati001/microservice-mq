package com.app.enquiry.service;

import com.app.enquiry.model.TestMsg;
import com.app.enquiry.utils.CustomJaxbMarshaller;
import com.app.enquiry.utils.CustomJaxbUnmarshaller;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//@XSlf4j
@Component
public class EnquiryService {
    @Autowired
    private CustomJaxbUnmarshaller customJaxbUnmarshaller;
    @Autowired
    private CustomJaxbMarshaller customJaxbMarshaller;

    public String getResource(String msg) throws Exception{
        StopWatch st = new StopWatch();
        st.start();
        customJaxbUnmarshaller.unmarshal(msg);
        st.stop();
        System.out.println("total time for unmarshalling "+st.getLastTaskTimeMillis());
        long salary = 1200*12;
        TestMsg newTestMsg = new TestMsg(2, "abcd", salary);
        st.start();
        customJaxbMarshaller.marshalObj(newTestMsg);
        st.stop();
        System.out.println("total time for marshalling "+st.getLastTaskTimeMillis());

        return "success";
    }
}
