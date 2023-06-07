package com.chatbot.Sahayakam.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessagesHistory {
	
	
	 private List<ReqResMessages> requestDataList;
	 private List<AssistantMessageHistory> responseDataList;
	 

	    public MessagesHistory() {
	        requestDataList = new ArrayList<>();
	        responseDataList =  new ArrayList<>();
	    }

	    public void addRequestData(String requestData) {
	    	ReqResMessages newRequestData = new ReqResMessages(requestData);
	        requestDataList.add(newRequestData);
	    }
	    
	    public void addResponseData(String responseData) {
	    	AssistantMessageHistory newAssistantMessageHistory = new AssistantMessageHistory(responseData);
	    	responseDataList.add(newAssistantMessageHistory);
	    }

	    public List<ReqResMessages> getRequestDataList() {
	        return requestDataList;
	    }
	    
	    public List<AssistantMessageHistory> getResponseDataList() {
	        return responseDataList;
	    }

}
