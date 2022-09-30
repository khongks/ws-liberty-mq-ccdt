package com.demo.messaging;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "SampleListenerMDBQM1")
public class SampleListenerQM1 implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println("MDB received from QM1: " + message.getBody(String.class));
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
