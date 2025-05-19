package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT )
class DemoSoapTest {
    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Test
    void testEncryptedSoapCall() throws Exception {
        MyRequest request = new MyRequest();
        request.setName( "John" );
        webServiceTemplate.marshalSendAndReceive( request, new SoapActionCallback( "myAction" ) );
    }
}
