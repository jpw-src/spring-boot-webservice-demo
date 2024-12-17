////////////////////////////////////////////////////////////////////////////////
// (C) 2024  SRC Security Research & Consulting GmbH
//           Emil-Nolde-Str. 7
//           D 53113 Bonn
////////////////////////////////////////////////////////////////////////////////

package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurer;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

@Configuration
public class WebServiceSecurityConfig implements WsConfigurer {

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementUsername( "mykey" );
        interceptor.setSecurementPassword( "password" );
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

    @Override
    public void addInterceptors( List<EndpointInterceptor > interceptors ) {
        try {
            interceptors.add( securityInterceptor() );
        }
        catch( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public void addArgumentResolvers( List<MethodArgumentResolver > argumentResolvers ) {
        // nothing to do
    }

    @Override
    public void addReturnValueHandlers( List<MethodReturnValueHandler > returnValueHandlers ) {
        // nothing to do
    }

}
