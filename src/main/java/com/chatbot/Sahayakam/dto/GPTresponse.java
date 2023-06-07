package com.chatbot.Sahayakam.dto;

import java.util.List;

public class GPTresponse {

    private List<Choice> choices;

    public GPTresponse() {
        // Default constructor
    }

    public GPTresponse(List<Choice> choices) {
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {

        private int index;
        private Message message;

        public Choice() {
            // Default constructor
        }

        public Choice(int index, Message message) {
            this.index = index;
            this.message = message;
        }

        public int getIndex() {
            return index;
        }

        public Message getMessage() {
            return message;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }
}
