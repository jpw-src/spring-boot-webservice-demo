package com.example.demo;

import java.io.StringWriter;
import java.lang.invoke.MethodHandles;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.interceptor.EndpointInterceptorAdapter;
import org.springframework.ws.soap.SoapMessage;

@Component
public class SoapServerLoggingInterceptor extends EndpointInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger( MethodHandles.lookup().lookupClass() );

    @Override
    public boolean handleRequest(
            MessageContext messageContext,
            Object endpoint
    ) throws Exception {
        SoapMessage soapRequest = (SoapMessage )messageContext.getRequest();
        logXmlContent( "Request", soapRequest );
        return true;
    }

    @Override
    public boolean handleResponse(
            MessageContext messageContext,
            Object endpoint
    ) throws Exception {
        SoapMessage soapResponse = (SoapMessage )messageContext.getResponse();
        logXmlContent( "Response", soapResponse );
        return true;
    }

    @Override
    public boolean handleFault(
            MessageContext messageContext,
            Object endpoint
    ) throws Exception {
        SoapMessage soapFault = (SoapMessage )messageContext.getResponse();
        logXmlContent( "Fault", soapFault );
        return true;
    }

    private void logXmlContent( String label, SoapMessage message ) {
        try {
            StringWriter writer = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform( message.getEnvelope().getSource(), new StreamResult( writer ) );
            logger.debug( "{} XML:\n{}", label, writer.toString() );
        }
        catch( Exception e ) {
            logger.warn( "Failed to log SOAP XML content for {}: {}", label, e.toString() );
        }
    }
}