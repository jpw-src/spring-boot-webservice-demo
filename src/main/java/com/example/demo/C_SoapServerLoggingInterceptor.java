package com.example.demo;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapMessage;

@Component
public class C_SoapServerLoggingInterceptor implements EndpointInterceptor {
    private static final Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );

    @Override
    public boolean handleRequest(
            MessageContext messageContext,
            Object endpoint
    ) throws Exception {
        SoapMessage soapRequest = (SoapMessage )messageContext.getRequest();
        logger.debug( "SOAP Endpoint: {}", endpoint.getClass().getName() );
        logger.debug( "SOAP Request: {}", soapRequest.getEnvelope().toString() );
        return true;
    }

    @Override
    public boolean handleResponse(
            MessageContext messageContext,
            Object endpoint
    ) throws Exception {
        SoapMessage soapResponse = (SoapMessage )messageContext.getResponse();
        logger.debug( "SOAP Endpoint: {}", endpoint.getClass().getName() );
        logger.debug( "SOAP Response: {}", soapResponse.getEnvelope().toString() );
        return true;
    }

    @Override
    public boolean handleFault(
            MessageContext messageContext,
            Object endpoint
    ) throws Exception {
        SoapMessage soapFault = (SoapMessage )messageContext.getResponse();
        logger.debug( "SOAP Endpoint: {}", endpoint.getClass().getName() );
        logger.debug( "SOAP Fault: {}", soapFault.getEnvelope().toString() );
        return true;
    }

    @Override
    public void afterCompletion(
            @SuppressWarnings( "unused" ) MessageContext messageContext,
            @SuppressWarnings( "unused" ) Object endpoint,
            @SuppressWarnings( "unused" ) Exception ex
    ) throws Exception {
        // do nothing
    }
}