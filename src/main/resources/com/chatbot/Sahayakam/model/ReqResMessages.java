package com.chatbot.Sahayakam.model;

import org.springframework.stereotype.Component;

@Component
public class ReqResMessages {
	
	private  String userMessage;

	
	
	
	public ReqResMessages(String userMessage) {
		super();
		this.userMessage = userMessage;
	}
	/**
	 * @return the userMessage
	 */
	public String getUserMessage() {
		return userMessage;
	}
	/**
	 * @param userMessage the userMessage to set
	 */
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	
	
	

}
