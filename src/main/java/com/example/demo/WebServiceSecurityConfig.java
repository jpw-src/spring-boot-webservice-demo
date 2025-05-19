package com.example.demo;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

@Configuration
public class WebServiceSecurityConfig {
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();

        interceptor.setValidationActions( "Encrypt" );
        interceptor.setValidationDecryptionCrypto( cryptoFactoryBean().getObject() );

        KeyStoreCallbackHandler callbackHandler = new KeyStoreCallbackHandler();
        callbackHandler.setPrivateKeyPassword( "password" );
        interceptor.setValidationCallbackHandler( callbackHandler );

        return interceptor;
    }

    @Bean( "server-crypto" )
    public CryptoFactoryBean cryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword( "password" );
        cryptoFactoryBean.setKeyStoreLocation( new ClassPathResource( "keystore-server.jks" ) );
        return cryptoFactoryBean;
    }
}
