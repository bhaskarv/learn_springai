package org.nvision.learn.springai.llama3.service;

import org.nvision.learn.springai.llama3.model.LlamaResponse;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

public interface AIService {
    LlamaResponse generateResponse(String prompt);
    LlamaResponse generateJoke(String topic);
    Flux<ChatResponse> streamJoke(String topic);
}
