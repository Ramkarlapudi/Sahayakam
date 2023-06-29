package com.chatbot.Sahayakam.Advice;

import com.chatbot.Sahayakam.exception.UserRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SahayakamExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserRequestException.class)
    public Map<String, String> handleUserInputException(UserRequestException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage" ,ex.getMessage());
        return errorMap;
    }
    @ExceptionHandler(HttpServerErrorException.class)
    public Map<String,String> httpServerErrorException(HttpServerErrorException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ex.getStatusCode().toString(),ex.getMessage());
        return errorMap;
    }
}
