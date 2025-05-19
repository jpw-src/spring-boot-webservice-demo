package com.example.demo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "MyRequest", namespace = "http://example.com/demo" )
public class MyRequest {
    private String name;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
