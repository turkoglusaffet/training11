package com.spring.training11.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.spring.training11.entity.TblUser;


@Component
public class JmsMessageListener {

	@JmsListener(destination = "myJmsQueue")
	public void handleMessage(TblUser user) {
		System.out.println("JMS message received ...");
		System.out.println(user);
	}

}
