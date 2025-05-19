package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;

public class SoapClientLoggingInterceptor extends ClientInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );

    @Override
    public boolean handleRequest( MessageContext messageContext ) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            messageContext.getRequest().writeTo( buffer );
            String payload = buffer.toString( StandardCharsets.UTF_8 );

            logger.debug( "SOAP Request:" );
            logger.debug( payload );
        }
        catch( Exception e ) {
            throw new RuntimeException( e );
        }

        return true;
    }

    @Override
    public boolean handleResponse( MessageContext messageContext ) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            messageContext.getResponse().writeTo( buffer );
            String payload = buffer.toString( StandardCharsets.UTF_8 );

            logger.debug( "SOAP Response:" );
            logger.debug( payload );
        }
        catch( Exception e ) {
            throw new RuntimeException( e );
        }

        return true;
    }
}
