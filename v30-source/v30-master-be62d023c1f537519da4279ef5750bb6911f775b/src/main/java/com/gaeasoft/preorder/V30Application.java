package com.gaeasoft.preorder;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.gaeasoft.preorder.filter.LogbackMdcFilter;
import com.gaeasoft.preorder.filter.MultiReadableHttpServletRequestFilter;

@SpringBootApplication
public class V30Application {

	public static void main(String[] args) {
		SpringApplication.run(V30Application.class, args);
	}

	@Bean
	public FilterRegistrationBean multiReadableHttpServletRequestFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		MultiReadableHttpServletRequestFilter multiReadableHttpServletRequestFilter = new MultiReadableHttpServletRequestFilter();
		registrationBean.setFilter(multiReadableHttpServletRequestFilter);
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean logbackMdcFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		LogbackMdcFilter logbackMdcFilter = new LogbackMdcFilter();
		registrationBean.setFilter(logbackMdcFilter);
		registrationBean.setOrder(2);
		return registrationBean;
	}
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {

	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
	    
        Connector ajpConnector = new Connector("AJP/1.3");
        ajpConnector.setPort(8009);
        ajpConnector.setSecure(false);
        ajpConnector.setAllowTrace(false);
        ajpConnector.setScheme("http");
        tomcat.addAdditionalTomcatConnectors(ajpConnector);

	    return tomcat;
	}
	
}
