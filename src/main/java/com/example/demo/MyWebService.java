package com.example.demo;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

@Endpoint
public class MyWebService {

    @SoapAction("http://example.com/demo/MyRequest")
    @ResponsePayload
    public MyResponse handleMyRequest(@RequestPayload MyRequest request) {
        MyResponse response = new MyResponse();
        response.setMessage("Hello, " + request.getName());
        return response;
    }
}
