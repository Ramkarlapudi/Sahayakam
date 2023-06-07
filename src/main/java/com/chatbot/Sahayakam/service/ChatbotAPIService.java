package com.chatbot.Sahayakam.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import jakarta.annotation.PostConstruct;

@Service
public class ChatbotAPIService {

	
	
	
	private static OpenAiService openAiService;

	@Value("${openai.key}")
	private String apiKey;

	@Value("${openai.timeout}")
	private int apiTimeout;

	private static final String GPT_MODEL = "gpt-3.5-turbo";

	private static final String SYSTEM_TASK_MESSAGE = "You are a helpful assistant.";

	@PostConstruct
	public void initchatbotAPIService() {
		openAiService = new OpenAiService(apiKey, Duration.ofSeconds(apiTimeout));
	}

	public String sendMessage(String message) {
		ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder().model(GPT_MODEL).temperature(0.8)
				.messages(List.of(new ChatMessage("system", SYSTEM_TASK_MESSAGE), new ChatMessage("user", message)))
				.build();

		StringBuilder builder = new StringBuilder();
		System.out.println("Calling createChatCompletion method  " + message);
		openAiService.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice -> {
			builder.append(choice.getMessage().getContent());
		});

		return builder.toString();
	}


}
