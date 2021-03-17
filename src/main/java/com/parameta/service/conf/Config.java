package com.parameta.service.conf;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class Config {

	@Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) 
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/service/*");
    }
 
    @Bean(name = "empleados")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema empleados) 
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SrvGuardarEmpleadosPort");
        wsdl11Definition.setLocationUri("/service/SrvEmpleados");
        wsdl11Definition.setTargetNamespace("http://www.parameta.com/service/empleados");
        wsdl11Definition.setSchema(empleados);
        return wsdl11Definition;
    }
 
    @Bean
    public XsdSchema countriesSchema() 
    {
        return new SimpleXsdSchema(new ClassPathResource("empleados.xsd"));
    }
}
