package cibertec.edu.pe.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;

import org.springframework.ws.config.annotation.EnableWs;

import org.springframework.ws.transport.http.MessageDispatcherServlet;

import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;

@EnableWs
@Configuration
public class SoapConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {

        MessageDispatcherServlet servlet =
                new MessageDispatcherServlet();

        servlet.setApplicationContext(applicationContext);

        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "citas")
    public DefaultWsdl11Definition defaultWsdl11Definition(
            XsdSchema citasSchema) {

        DefaultWsdl11Definition wsdl11Definition =
                new DefaultWsdl11Definition();

        wsdl11Definition.setPortTypeName("CitasPort");

        wsdl11Definition.setLocationUri("/ws");

        wsdl11Definition.setTargetNamespace(
                "http://cibertec.pe/citas");

        wsdl11Definition.setSchema(citasSchema);

        return wsdl11Definition;
    }

    @Bean
    public XsdSchema citasSchema() {
        return new SimpleXsdSchema(
                new ClassPathResource("citas.xsd"));
    }
}