package com.spring.training11.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWepAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
		context.setServletContext(servletContext);

		// MVC + REST
		DispatcherServlet mvcDispatcherServlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic mapping = servletContext.addServlet("mvcDispatcher", mvcDispatcherServlet);
		mapping.addMapping("/mvc/*");
		mapping.setLoadOnStartup(1);

		servletContext.addListener(new ContextLoaderListener(context));
	}

}
