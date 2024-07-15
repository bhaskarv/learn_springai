package org.nvision.learn.springai.llama3.service.impl;

import org.nvision.learn.springai.llama3.model.LlamaResponse;
import org.nvision.learn.springai.llama3.service.AIService;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class LlamaAIService implements AIService {

    private final ChatModel chatModel;

    public LlamaAIService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public LlamaResponse generateResponse(String prompt) {
        System.out.printf("Prompt %s",prompt);
        final String llamaResponse = chatModel.call(prompt);

        System.out.println("RESPOMSE : "+llamaResponse.toString());
        return new LlamaResponse(llamaResponse);
    }

    @Override
    public LlamaResponse generateJoke(String topic) {
        System.out.printf(" TOPIC %s",topic);
        final String llamaResp = chatModel.call(String.format("Tell me a joke about %s", topic));
        System.out.println("RESPOMSE : "+llamaResp.toString());
        return new LlamaResponse(llamaResp);
    }

    @Override
    public Flux<ChatResponse> streamJoke(String topic) {
        System.out.printf(" TOPIC %s",topic);
        Prompt prompt = new Prompt(new UserMessage(String.format("Tell me a joke about %s", topic)));
        Flux<ChatResponse> resp = chatModel.stream(prompt);
        System.out.println("RESPONSE  : "+resp);
        return  resp;
    }
}
