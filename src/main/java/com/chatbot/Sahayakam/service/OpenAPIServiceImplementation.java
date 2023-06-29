package com.chatbot.Sahayakam.service;

import com.chatbot.Sahayakam.controller.ChatbotAPIController;
import com.chatbot.Sahayakam.dto.ChatCompletion;
import com.chatbot.Sahayakam.dto.GPTrequest;
import com.chatbot.Sahayakam.dto.GPTresponse;
import com.chatbot.Sahayakam.dto.Message;
import com.chatbot.Sahayakam.entity.ReqResMessageEntity;
import com.chatbot.Sahayakam.repository.ReqResMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenAPIServiceImplementation implements OpenAPIService {

    Logger logger = LoggerFactory.getLogger(OpenAPIServiceImplementation.class);

    @Autowired
    private ReqResMessageRepository reqResMessageRepository;

//    @Autowired
//    private ReqResMessageEntity reqResMessageEntity;

    private String model = "gpt-3.5-turbo";

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;


    public String conversationalChat(String prompt) {
        logger.info(" ** Entering conversationalChat Service **");

        ChatCompletion chatCompletion = null;
        List<Message> messages = null;
        List<ReqResMessageEntity> reqResMessageEntityList = new ArrayList<ReqResMessageEntity>();
        ReqResMessageEntity reqResMessageEntity = new ReqResMessageEntity();

        if (reqResMessageRepository.existsBy()) {
            reqResMessageEntityList = reqResMessageRepository.findAllByOrderByIdAsc();
             messages = new ArrayList<Message>();
            Message systemMsg = new Message();
            systemMsg.setRole("system");
            systemMsg.setContent("You are a helpful assistant.");
            messages.add(systemMsg);

            for (ReqResMessageEntity MessageEntity : reqResMessageEntityList) {
                Message userMsg = new Message();
                userMsg.setRole("user");
                userMsg.setContent(MessageEntity.getReqMessage());
                messages.add(userMsg);

                Message assistantMsg = new Message();
                assistantMsg.setRole("assistant");
                assistantMsg.setContent(MessageEntity.getResMessage());
                messages.add(assistantMsg);
            }

            Message newMsg = new Message();
            newMsg.setRole("user");
            newMsg.setContent(prompt);
            messages.add(newMsg);

            chatCompletion = new ChatCompletion();
            chatCompletion.setModel("gpt-3.5-turbo");
            chatCompletion.setMessages(messages);

        } else {
            chatCompletion = new ChatCompletion();
            messages =  new ArrayList<Message>();
            chatCompletion.setModel("gpt-3.5-turbo");
            Message userMsg = new Message();
            userMsg.setRole("user");
            userMsg.setContent(prompt);
            messages.add(userMsg);
            chatCompletion.setMessages(messages);
        }

        HttpEntity<ChatCompletion> entity = new HttpEntity<>(chatCompletion);

        logger.info(" ** Req Res chatCompletion ** " + entity.getBody().getModel().toString() );

        entity.getBody().getMessages().stream().forEach((m) ->{
            logger.info(" ** Req Res msgList ** " + m.getRole().toString() );
            logger.info(" ** Req Res msgList ** " + m.getContent().toString() );
            System.out.println(m.getContent());
        });

        ResponseEntity<GPTresponse> response = template.postForEntity(apiURL, entity, GPTresponse.class);
        logger.info(" ** Response Received from  GPT API ** " + response.getStatusCode());
        if (response.getStatusCode().is2xxSuccessful()) {
            GPTresponse gptResponse = response.getBody();
            if (gptResponse != null && gptResponse.getChoices() != null && !gptResponse.getChoices().isEmpty()) {
                logger.info(" ** Logging Req/Res to H2 DB **");

                reqResMessageEntity.setReqMessage(prompt);
                reqResMessageEntity.setResMessage(gptResponse.getChoices().get(0).getMessage().getContent());
                reqResMessageRepository.save(reqResMessageEntity);
                logger.info(" ** Exiting from chatmsg API Service  with status code " + response.getStatusCode() + " **");
                return gptResponse.getChoices().get(0).getMessage().getContent();
            }

        } else {
            logger.error(" ** Error Received from GPT API **");
            throw new HttpServerErrorException(response.getStatusCode());
        }
        return "No response from the GPT API.";
    }

}
