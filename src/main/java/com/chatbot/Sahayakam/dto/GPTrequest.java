package com.chatbot.Sahayakam.dto;

import java.util.ArrayList;
import java.util.List;

public class GPTrequest {

    private String model;
    private List<Message> messages;

    private String prompt;

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public GPTrequest() {
    }

    public GPTrequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public GPTrequest(String model, String prompt) {
        this.model = model;
        this.messages =  new ArrayList<>();
        this.messages.add(new Message("user",prompt));
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
