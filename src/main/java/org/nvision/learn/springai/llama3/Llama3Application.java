package org.nvision.learn.springai.llama3;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Llama3Application {

	public static void main(String[] args) {
		SpringApplication.run(Llama3Application.class, args);
	}

	@Bean
	public VectorStore vectorStore(OllamaEmbeddingModel embeddingModel) {
		return new SimpleVectorStore(embeddingModel);
	}

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
}