package com.example.demo;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MyResponse", namespace = "http://example.com/demo")
public class MyResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
