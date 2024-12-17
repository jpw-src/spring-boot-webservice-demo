package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ws.client.core.WebServiceTemplate;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebServiceTestConfig.class)
class DemoSoapTestStandalone {
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
