package com.chatbot.Sahayakam.model;

import org.springframework.stereotype.Component;

@Component
public class AssistantMessageHistory {

	private String assisResponse;

	
	

	public AssistantMessageHistory(String assisResponse) {
		super();
		this.assisResponse = assisResponse;
	}

	/**
	 * @return the assisResponse
	 */
	public String getAssisResponse() {
		return assisResponse;
	}

	/**
	 * @param assisResponse the assisResponse to set
	 */
	public void setAssisResponse(String assisResponse) {
		this.assisResponse = assisResponse;
	}
	
	
}
