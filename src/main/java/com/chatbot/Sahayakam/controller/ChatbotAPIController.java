package com.chatbot.Sahayakam.controller;

import com.chatbot.Sahayakam.dto.GPTrequest;
import com.chatbot.Sahayakam.dto.GPTresponse;
import com.chatbot.Sahayakam.dto.UserRequest;
import com.chatbot.Sahayakam.exception.UserRequestException;
import com.chatbot.Sahayakam.service.OpenAPIServiceImplementation;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.chatbot.Sahayakam.service.ChatbotAPIService;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController()
@RequestMapping("ChatBotAPI")
@CrossOrigin(origins = "*")
public class ChatbotAPIController {

	private static final Logger logger = LogManager.getLogger(ChatbotAPIController.class);
	@Autowired
	private ChatbotAPIService chatbotAPIService;
	@Autowired
	private OpenAPIServiceImplementation openAPIServiceImplementation;

	private String model = "gpt-3.5-turbo";

	@Value(("${openai.api.url}"))
	private String apiURL;



	@Autowired
	private RestTemplate template;
	
	@PostMapping(path = "/message",consumes = APPLICATION_JSON_VALUE)
	public String getGPTAPIResponse(@RequestBody String message) {
		String res = null;
		if(!message.isEmpty() && message != null) {
			System.out.println("Calling sendMessage method  :: " + message);
			 res =	chatbotAPIService.sendMessage(message);
		}else {
			System.out.println("ERROR");
		}
		
		return res;
		
	}


	@PostMapping(path="/chat")
	public String chatmsg(@RequestBody String msg) throws JsonProcessingException {
		logger.info(" ** Entering chatmsg API Called **");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		GPTrequest request=new GPTrequest(model, msg);

		HttpEntity<GPTrequest> entity = new HttpEntity<>(request, headers);
		logger.info(" ** Calling GPT API **");
		ResponseEntity<GPTresponse> response = template.postForEntity(apiURL, entity, GPTresponse.class);
		logger.info(" ** Response Received from  GPT API ** "+ response.getStatusCode());
		if (response.getStatusCode().is2xxSuccessful()) {

			GPTresponse gptResponse = response.getBody();
			if (gptResponse != null && gptResponse.getChoices() != null && !gptResponse.getChoices().isEmpty()) {
				logger.info(" ** Exiting from chatmsg API Service  with status code " + response.getStatusCode() + " **" );
				return gptResponse.getChoices().get(0).getMessage().getContent();
			}
			return "No response from the GPT API.";
		} else {
			logger.error(" ** Error Received from GPT API **");
			return "Error: " + response.getStatusCode();
		}

	}



	@PostMapping(path="/conversationalChat")
	public ResponseEntity<String> conversationalChat(@RequestBody UserRequest msg) throws JsonProcessingException, UserRequestException {
		logger.info(" ** Entering conversationalChat API ** "+msg.getPrompt().toString());
		if (!StringUtils.isEmpty(msg.getPrompt())   || msg.getPrompt()!=null) {
			String res = openAPIServiceImplementation.conversationalChat(msg.getPrompt());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			logger.info(" ** Exiting conversationalChat API **");
			return new ResponseEntity<>("{\"response\": \"" + res + "\"}", headers, HttpStatus.OK);
		}else {
             throw new UserRequestException("Request should not be Empty or Null");
		}



	}
	}


	
	
	
	
	

