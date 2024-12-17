package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT )
class DemoSoapTest {
    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Test
    void testEncryptedSoapCall() throws Exception {
        MyRequest request = new MyRequest();
        request.setName("John");
        MyResponse response = (MyResponse) webServiceTemplate.marshalSendAndReceive(request);

        assertNotNull(response);
        assertEquals("Hello, John", response.getMessage());
    }
}
