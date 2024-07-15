package org.nvision.learn.springai.llama3.controller;

import org.nvision.learn.springai.llama3.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocuChatController {

    private ChatService chatService;

    public DocuChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("chat/doc")
    String chatWithDocument(@RequestBody String prompt) {
        return chatService.chatWithDocument(prompt);
    }
}
