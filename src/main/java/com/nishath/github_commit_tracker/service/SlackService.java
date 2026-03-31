package com.nishath.github_commit_tracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SlackService {

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(String message) {
        Map<String, String> payload = new HashMap<>();
        payload.put("text", message);
        System.out.println("sending message to slack");
        restTemplate.postForObject(webhookUrl, payload, String.class);
    }
}
