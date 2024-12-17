# Spring boot webservice demo with message encryption

This minimal demo project shall show the problem, that the server is not able to decrypt a message.

To start the server, run

    gradle bootRun

which gives the output

    > Task :bootRun
    
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
    
     :: Spring Boot ::                (v3.4.0)
    
    2024-12-17T17:16:05.536+01:00  INFO 25109 --- [           main] com.example.demo.SoapWsDemoApplication   : Starting SoapWsDemoApplication using Java 17.0.3 with PID 25109 (/home/jpw/work/soap-demo/build/classes/java/main started by jpw in /home/jpw/work/soap-demo)
    2024-12-17T17:16:05.539+01:00 DEBUG 25109 --- [           main] com.example.demo.SoapWsDemoApplication   : Running with Spring Boot v3.4.0, Spring v6.2.0
    2024-12-17T17:16:05.540+01:00  INFO 25109 --- [           main] com.example.demo.SoapWsDemoApplication   : No active profile set, falling back to 1 default profile: "default"
    2024-12-17T17:16:06.132+01:00  WARN 25109 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.ws.config.annotation.DelegatingWsConfiguration' of type [org.springframework.ws.config.annotation.DelegatingWsConfiguration$$SpringCGLIB$$0] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). The currently created BeanPostProcessor [annotationActionEndpointMapping] is declared through a non-static factory method on that class; consider declaring it as static instead.
    2024-12-17T17:16:06.161+01:00  INFO 25109 --- [           main] .w.s.a.s.AnnotationActionEndpointMapping : Supporting [WS-Addressing August 2004, WS-Addressing 1.0]
    2024-12-17T17:16:06.344+01:00  INFO 25109 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
    2024-12-17T17:16:06.355+01:00  INFO 25109 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
    2024-12-17T17:16:06.355+01:00  INFO 25109 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.33]
    2024-12-17T17:16:06.400+01:00  INFO 25109 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
    2024-12-17T17:16:06.401+01:00  INFO 25109 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 811 ms
    2024-12-17T17:16:06.471+01:00 DEBUG 25109 --- [           main] s.w.w.w.p.InliningXsdSchemaTypesProvider : Inlining SimpleXsdSchema{http://example.com/demo}
    2024-12-17T17:16:06.478+01:00 DEBUG 25109 --- [           main] o.s.w.w.w.p.DefaultMessagesProvider      : Looking for elements in schema with target namespace [http://example.com/demo]
    2024-12-17T17:16:06.480+01:00 DEBUG 25109 --- [           main] o.s.w.w.w.p.DefaultMessagesProvider      : Creating message [{http://example.com/demo}MyRequest]
    2024-12-17T17:16:06.480+01:00 DEBUG 25109 --- [           main] o.s.w.w.w.p.DefaultMessagesProvider      : Creating message [{http://example.com/demo}MyResponse]
    2024-12-17T17:16:06.481+01:00 DEBUG 25109 --- [           main] o.s.w.w.w.p.SuffixBasedPortTypesProvider : Creating port type [{http://example.com/demo}DemoPort]
    2024-12-17T17:16:06.482+01:00 DEBUG 25109 --- [           main] o.s.w.w.w.p.SuffixBasedPortTypesProvider : Adding operation [My] to port type [{http://example.com/demo}DemoPort]
    2024-12-17T17:16:06.482+01:00 DEBUG 25109 --- [           main] o.s.w.w.wsdl11.provider.Soap11Provider   : Creating binding [{http://example.com/demo}DemoPortSoap11]
    2024-12-17T17:16:06.483+01:00 DEBUG 25109 --- [           main] o.s.w.w.wsdl11.provider.Soap11Provider   : Creating service [{http://example.com/demo}DemoPortService]
    2024-12-17T17:16:06.484+01:00 DEBUG 25109 --- [           main] o.s.w.w.wsdl11.provider.Soap11Provider   : Adding port [DemoPortSoap11] to service [{http://example.com/demo}DemoPortService]
    2024-12-17T17:16:06.693+01:00 DEBUG 25109 --- [           main] yloadRootAnnotationMethodEndpointMapping : Looking for endpoints in application context: org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@78a773fd, started on Tue Dec 17 17:16:05 CET 2024
    2024-12-17T17:16:06.694+01:00 DEBUG 25109 --- [           main] yloadRootAnnotationMethodEndpointMapping : Mapped [{http://example.com/demo}MyRequest] onto endpoint [public com.example.demo.MyResponse com.example.demo.MyWebService.handleMyRequest(com.example.demo.MyRequest)]
    2024-12-17T17:16:06.705+01:00 DEBUG 25109 --- [           main] oapActionAnnotationMethodEndpointMapping : Looking for endpoints in application context: org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@78a773fd, started on Tue Dec 17 17:16:05 CET 2024
    2024-12-17T17:16:06.713+01:00 DEBUG 25109 --- [           main] o.s.w.s.e.a.DefaultMethodEndpointAdapter : No MethodArgumentResolvers set, using defaults: [org.springframework.ws.server.endpoint.adapter.method.dom.DomPayloadMethodProcessor@551e4c6d, org.springframework.ws.server.endpoint.adapter.method.MessageContextMethodArgumentResolver@625a9c5d, org.springframework.ws.server.endpoint.adapter.method.SourcePayloadMethodProcessor@5d1e0fbb, org.springframework.ws.server.endpoint.adapter.method.XPathParamMethodArgumentResolver@2eed37f4, org.springframework.ws.soap.server.endpoint.adapter.method.SoapMethodArgumentResolver@9b76b60, org.springframework.ws.soap.server.endpoint.adapter.method.SoapHeaderElementMethodArgumentResolver@3fb9a67f, org.springframework.ws.server.endpoint.adapter.method.jaxb.XmlRootElementPayloadMethodProcessor@127705e4, org.springframework.ws.server.endpoint.adapter.method.jaxb.JaxbElementPayloadMethodProcessor@5562c2c9, org.springframework.ws.server.endpoint.adapter.method.StaxPayloadMethodArgumentResolver@673c4f6e]
    2024-12-17T17:16:06.714+01:00 DEBUG 25109 --- [           main] o.s.w.s.e.a.DefaultMethodEndpointAdapter : No MethodReturnValueHandlers set, using defaults: [org.springframework.ws.server.endpoint.adapter.method.dom.DomPayloadMethodProcessor@15c487a8, org.springframework.ws.server.endpoint.adapter.method.SourcePayloadMethodProcessor@3f36e8d1, org.springframework.ws.server.endpoint.adapter.method.jaxb.XmlRootElementPayloadMethodProcessor@7c011174, org.springframework.ws.server.endpoint.adapter.method.jaxb.JaxbElementPayloadMethodProcessor@794366a5]
    2024-12-17T17:16:06.941+01:00  INFO 25109 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
    2024-12-17T17:16:06.949+01:00  INFO 25109 --- [           main] com.example.demo.SoapWsDemoApplication   : Started SoapWsDemoApplication in 1.799 seconds (process running for 2.032)
    
You can get the wsdl by running

    curl -v http://localhost:8080/ws/demo.wsdl

To show the problem with the decryption within the server, run

    gradle test --tests DemoSoapTestStandalone
    
This test will fail and the server log says:

    2024-12-17T17:17:15.946+01:00  INFO 25109 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring MessageDispatcherServlet 'messageDispatcherServlet'
    2024-12-17T17:17:15.946+01:00  INFO 25109 --- [nio-8080-exec-1] o.s.w.t.http.MessageDispatcherServlet    : Initializing Servlet 'messageDispatcherServlet'
    2024-12-17T17:17:15.949+01:00  INFO 25109 --- [nio-8080-exec-1] o.s.ws.soap.saaj.SaajSoapMessageFactory  : Creating SAAJ 1.3 MessageFactory with SOAP 1.1 Protocol
    2024-12-17T17:17:15.956+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.ws.soap.saaj.SaajSoapMessageFactory  : Using MessageFactory class [com.sun.xml.messaging.saaj.soap.ver1_1.SOAPMessageFactory1_1Impl]
    2024-12-17T17:17:15.956+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.t.http.MessageDispatcherServlet    : No WebServiceMessageFactory found in servlet 'messageDispatcherServlet': using default
    2024-12-17T17:17:15.959+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.t.http.MessageDispatcherServlet    : No MessageDispatcher found in servlet 'messageDispatcherServlet': using default
    2024-12-17T17:17:15.959+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.t.http.MessageDispatcherServlet    : Published [org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition@43642280] as demo.wsdl
    2024-12-17T17:17:15.960+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.t.http.MessageDispatcherServlet    : Published [SimpleXsdSchema{http://example.com/demo}] as demoSchema.xsd
    2024-12-17T17:17:15.962+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.t.http.MessageDispatcherServlet    : enableLoggingRequestDetails='false': request parameters and headers will be masked to prevent unsafe logging of potentially sensitive data
    2024-12-17T17:17:15.962+01:00  INFO 25109 --- [nio-8080-exec-1] o.s.w.t.http.MessageDispatcherServlet    : Completed initialization in 16 ms
    2024-12-17T17:17:15.968+01:00 DEBUG 25109 --- [nio-8080-exec-1] .WebServiceMessageReceiverHandlerAdapter : Accepting incoming [org.springframework.ws.transport.http.HttpServletConnection@6ae96f72] at [http://localhost:8080/ws]
    2024-12-17T17:17:16.037+01:00 TRACE 25109 --- [nio-8080-exec-1] o.s.ws.server.MessageTracing.received    : Received request [<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"><SOAP-ENV:Header><wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" SOAP-ENV:mustUnderstand="1"><xenc:EncryptedKey xmlns:xenc="http://www.w3.org/2001/04/xmlenc#" Id="EK-5288073c-5679-4261-83d5-63fd31c9db1d"><xenc:EncryptionMethod Algorithm="http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p"/><ds:KeyInfo xmlns:ds="http://www.w3.org/2000/09/xmldsig#"><wsse:SecurityTokenReference><ds:X509Data><ds:X509IssuerSerial><ds:X509IssuerName>CN=server,OU=MyOrg,O=MyCompany,L=City,ST=State,C=DE</ds:X509IssuerName><ds:X509SerialNumber>11415732272752847481</ds:X509SerialNumber></ds:X509IssuerSerial></ds:X509Data></wsse:SecurityTokenReference></ds:KeyInfo><xenc:CipherData><xenc:CipherValue>MGun/IOEqaxUvfarx+3z2TCYtcv4STaaltwAQ5JyfPSzVMVNvl6VI5y+aU0u/nKHJWnxP9mQfuTfs2AxihOOg0chm01g44gTQpSkwqMkyGSDtH6mWH/o2U4NenuxKgXlRowrHyrD7Ik7rdmchK88XksGCZ/drj9FVgqDlaPYtpN7WTm157z2OkwR6Oye8NZ93/2nyzbQERCxp7jg2a7xA2ymcLQVyXSiLa0HvNw1TLS9DxJixw7Io7+IPHyobMRD5OLz7ZXxzNIvTPSFMhe2VAuzwrqWEguGzkC+RD3UAy/mXGcLff47MMBuEpasmsu4X7AntQxZqO9zM2x82pqr6A==</xenc:CipherValue></xenc:CipherData><xenc:ReferenceList><xenc:DataReference URI="#ED-e7b7af5c-44b6-4a4f-b742-d2a0b69fe07f"/></xenc:ReferenceList></xenc:EncryptedKey></wsse:Security></SOAP-ENV:Header><SOAP-ENV:Body><xenc:EncryptedData xmlns:xenc="http://www.w3.org/2001/04/xmlenc#" Id="ED-e7b7af5c-44b6-4a4f-b742-d2a0b69fe07f" Type="http://www.w3.org/2001/04/xmlenc#Content"><xenc:EncryptionMethod Algorithm="http://www.w3.org/2001/04/xmlenc#aes128-cbc"/><ds:KeyInfo xmlns:ds="http://www.w3.org/2000/09/xmldsig#"><wsse:SecurityTokenReference xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsse11="http://docs.oasis-open.org/wss/oasis-wss-wssecurity-secext-1.1.xsd" wsse11:TokenType="http://docs.oasis-open.org/wss/oasis-wss-soap-message-security-1.1#EncryptedKey"><wsse:Reference URI="#EK-5288073c-5679-4261-83d5-63fd31c9db1d"/></wsse:SecurityTokenReference></ds:KeyInfo><xenc:CipherData><xenc:CipherValue>AS/5GX1vR2Jlj8MvV6VZNazf/P3lVcSPws7cNydu57kgUvaJ5oSaqbytfAW5AkVJCQJAbZRhq1GIBRzsnoA71I0iToEXSxagH8W5Grv6T6eXUWsBBjl4ehdYxwpzcGI1aM5XahOF6fIn1hNCyRA5LA==</xenc:CipherValue></xenc:CipherData></xenc:EncryptedData></SOAP-ENV:Body></SOAP-ENV:Envelope>]
    2024-12-17T17:17:16.041+01:00 DEBUG 25109 --- [nio-8080-exec-1] yloadRootAnnotationMethodEndpointMapping : Looking up endpoint for [{http://www.w3.org/2001/04/xmlenc#}EncryptedData]
    2024-12-17T17:17:16.042+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.soap.server.SoapMessageDispatcher  : Endpoint mapping [org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping@6bc1e48e] has no mapping for request
    2024-12-17T17:17:16.042+01:00 DEBUG 25109 --- [nio-8080-exec-1] oapActionAnnotationMethodEndpointMapping : Looking up endpoint for []
    2024-12-17T17:17:16.042+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.soap.server.SoapMessageDispatcher  : Endpoint mapping [org.springframework.ws.soap.server.endpoint.mapping.SoapActionAnnotationMethodEndpointMapping@38f895d4] has no mapping for request
    2024-12-17T17:17:16.043+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.soap.server.SoapMessageDispatcher  : Endpoint mapping [org.springframework.ws.soap.addressing.server.AnnotationActionEndpointMapping@5539aaed] has no mapping for request
    2024-12-17T17:17:16.044+01:00  WARN 25109 --- [nio-8080-exec-1] o.s.ws.server.EndpointNotFound           : No endpoint mapping found for [SaajSoapMessage {http://www.w3.org/2001/04/xmlenc#}EncryptedData]
    2024-12-17T17:17:16.045+01:00 DEBUG 25109 --- [nio-8080-exec-1] o.s.w.t.http.MessageDispatcherServlet    : Completed 404 NOT_FOUND
The problem is shown in the latest log messages:

    No endpoint mapping found for [SaajSoapMessage {http://www.w3.org/2001/04/xmlenc#}EncryptedData]
    
The server should automatically decrypt the request and route it to the correct endpoint.

**Any help in getting this demo project up and running is welcome!**