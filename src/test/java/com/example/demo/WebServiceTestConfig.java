package com.example.demo;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

@Configuration
public class WebServiceTestConfig {
    @Bean
    public WebServiceTemplate webServiceTemplate() throws Exception {
        WebServiceTemplate result = new WebServiceTemplate();
        result.setDefaultUri("http://localhost:8080/ws");
        result.setInterceptors( new ClientInterceptor[] { securityInterceptor(), new SoapClientLoggingInterceptor() } );
        result.setMarshaller( marshaller() );
        return result;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.example.demo");
        return marshaller;
    }

    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions( "Encrypt" );
        interceptor.setSecurementEncryptionUser( "server-cert" );
        interceptor.setSecurementEncryptionCrypto( cryptoFactoryBean().getObject() );
        interceptor.setSecurementEncryptionParts( "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body" );
        return interceptor;
    }

    @Bean( "test-crypto" )
    public CryptoFactoryBean cryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword( "password" );
        cryptoFactoryBean.setKeyStoreLocation( new ClassPathResource( "keystore-client.jks" ) );
        return cryptoFactoryBean;
    }
}
