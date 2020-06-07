package com.app.enquiry.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="testmessage")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name="testmessage", propOrder= {"id","name","salary"})
public class TestMsg {

    @XmlElement(name="id")
    private int id;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="salary")
    private long salary;
    public TestMsg(){

    }

    public TestMsg(int id, String name, long salary) {
        this.id=id;
        this.name=name;
        this.salary=salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Resource has id=["+id+"], name=["+name+"], salary=["+salary+"]";
    }
}
