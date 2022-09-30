package com.demo.messaging;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "SampleListenerMDBQM2")
public class SampleListenerQM2 implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println("MDB received from QM2: " + message.getBody(String.class));
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
