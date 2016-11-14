package com.spring.training11.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

@Configuration
@EnableJms
public class JmsConfig {
	
	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("vm://localhost?broker.persistent=false");
		return connectionFactory;
	}
	
	@Bean
	@Autowired
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setDefaultDestinationName("myJmsQueue");
		//jmsTemplate.setDefaultDestination(new ActiveMQTopic("myJmsTopic"));
		return jmsTemplate;
	}
	
	@Bean
	@Autowired
	public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory){
		DefaultJmsListenerContainerFactory listener = new DefaultJmsListenerContainerFactory();
		listener.setConnectionFactory(connectionFactory);
		listener.setDestinationResolver(new DynamicDestinationResolver());
		listener.setConcurrency("3-10");
		return listener;
	}

}
