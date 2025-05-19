package com.example.demo;

import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class WhitespaceInjectorInterceptor extends ClientInterceptorAdapter {
    @Override
    public boolean handleRequest( MessageContext messageContext ) throws WebServiceClientException {
        SaajSoapMessage soapMessage = (SaajSoapMessage )messageContext.getRequest();
        Document doc = soapMessage.getDocument();
        NodeList bodyList = doc.getElementsByTagNameNS( "http://schemas.xmlsoap.org/soap/envelope/", "Body" );
        Element body = (Element )bodyList.item( 0 );
        Node myRequest = body.getFirstChild();
        
        /*
         * this whitespace node creates a ClassCastException after decryption on the server side:
         * 
         * class com.sun.org.apache.xerces.internal.dom.TextImpl cannot be cast
         * to class jakarta.xml.soap.Node
         * (com.sun.org.apache.xerces.internal.dom.TextImpl is in module
         * java.xml of loader 'bootstrap'; jakarta.xml.soap.Node is in unnamed
         * module of loader 'app')
         */
        Text newline = doc.createTextNode( "\n\n" );
        
        body.insertBefore( newline, myRequest );
        return true;
    }
}
