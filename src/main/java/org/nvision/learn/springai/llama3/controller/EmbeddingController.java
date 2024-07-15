package org.nvision.learn.springai.llama3.controller;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmbeddingController {

    private final EmbeddingModel embeddingModel;

    public EmbeddingController(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @GetMapping("/embed")
    String embed(@RequestParam(defaultValue = "And Gondalf yelled : 'you shall not pass'") String message) {
        var embedding = embeddingModel.embed(message);
        return String.format("embeddings : %s \\n Size of the embedding vector %s ", embedding, embedding.size());

    }

    @GetMapping("/embed/ollama-options")
    String embedWithOllamaOptions(@RequestParam(defaultValue = "And Gondalf yelled : 'you shall not pass'") String message) {
        var embeddings = embeddingModel.call(new EmbeddingRequest(List.of(message), OllamaOptions.create().withModel(OllamaModel.LLAMA3.id())))
                                    .getResult()
                                    .getOutput();

        return String.format("embeddings : %s \\n Size of the embedding vector %s ", embeddings, embeddings.size());
    }
}
