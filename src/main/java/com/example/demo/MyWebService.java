package com.example.demo;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MyWebService {

    private static final String NAMESPACE_URI = "http://example.com/demo";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MyRequest")
    @ResponsePayload
    public MyResponse handleMyRequest(@RequestPayload MyRequest request) {
        MyResponse response = new MyResponse();
        response.setMessage("Hello, " + request.getName());
        return response;
    }
}
