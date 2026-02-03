package com.dlms.backend.chatbot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatbotController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ChatbotController.class);

    @Value("${chatbot.service.url:http://localhost:8000}")
    private String chatbotServiceUrl;

    @PostMapping
    public ResponseEntity<?> chat(@RequestBody Map<String, Object> request) {
        logger.info("Received Chat Request. Forwarding to Chatbot Service at: {}", chatbotServiceUrl);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        try {
            // Forward request to Python Chatbot Service
            String targetUrl = chatbotServiceUrl + "/api/chat";
            logger.debug("Forwarding to: {}", targetUrl);

            ResponseEntity<String> response = restTemplate.postForEntity(targetUrl, entity, String.class);
            logger.info("Chatbot Service Response Code: {}", response.getStatusCode());

            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (org.springframework.web.client.HttpClientErrorException
                | org.springframework.web.client.HttpServerErrorException e) {
            logger.error("Chatbot Service returned Error. Status: {}, Body: {}", e.getStatusCode(),
                    e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            logger.error("Failed to communicate with Chatbot Service: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Chatbot service unavailable: " + e.getMessage()));
        }
    }
}
