package com.chatbot.Sahayakam.dto;

import java.util.List;

public class ChatCompletion {

    private String model;
    private List<Message> messages;

    // Getters and setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
