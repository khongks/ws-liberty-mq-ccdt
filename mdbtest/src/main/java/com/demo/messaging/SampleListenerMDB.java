package com.demo.messaging;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "SampleListenerMDB")
public class SampleListenerMDB implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println("MDB received: " + message.getBody(String.class));
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
