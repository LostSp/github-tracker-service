package com.nishath.github_commit_tracker.controller;

import com.nishath.github_commit_tracker.dto.GitHubWebhookPayload;
import com.nishath.github_commit_tracker.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private CommitService commitService;

    @PostMapping("/github")
    public String receiveWebhook(@RequestBody GitHubWebhookPayload payload) {
        System.out.println("HI");
        System.out.println("payload commits: "+payload.getCommits());
        commitService.processWebhook(payload);
        return "Webhook processed successfully";
    }

}
