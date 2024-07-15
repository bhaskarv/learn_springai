//Refer the site : https://github.com/klindziukp/spring-ai-ollama-llama
package org.nvision.learn.springai.llama3.controller;

import org.json.HTTP;
import org.nvision.learn.springai.llama3.model.LlamaResponse;
import org.nvision.learn.springai.llama3.service.AIService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class LlamaController {

    private final AIService llamaAIservice;

    @Autowired

    public LlamaController(AIService llamaAIservice) {
        this.llamaAIservice = llamaAIservice;
    }

    @GetMapping("/api/v1/ai/generate")
    public ResponseEntity<LlamaResponse> generate(
                        @RequestParam(value="promptMessage", defaultValue = "What is Spring AI") String prompt) {

        final LlamaResponse resp = llamaAIservice.generateResponse(prompt);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/api/v1/ai/generate/joke/{topic}")
    public ResponseEntity<LlamaResponse> generateJoke(@PathVariable String topic) {
        final LlamaResponse resp = llamaAIservice.generateJoke(topic);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/api/v1/ai/stream/joke/{topic}")
    public ResponseEntity<Flux<ChatResponse>> streamJoke(@PathVariable String topic) {
        //return llamaAIservice.streamJoke(topic);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_EVENT_STREAM).body(llamaAIservice.streamJoke(topic));
    }

}
