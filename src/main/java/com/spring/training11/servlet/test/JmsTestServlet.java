package com.spring.training11.servlet.test;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.spring.training11.entity.TblUser;
import com.spring.training11.jpa.UserRepository;


/**
 * Servlet implementation class ConfigTestServlet
 */
@WebServlet(urlPatterns = { "/jmstest" })
public class JmsTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		
		TblUser user = repository.findById(id);
		jmsTemplate.convertAndSend(user);
		
		Writer writer = response.getWriter();
		response.setContentType("text/html");
		writer.write("JMS message sent ...");

	}

}
