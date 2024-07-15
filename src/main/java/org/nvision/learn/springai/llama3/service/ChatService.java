package org.nvision.learn.springai.llama3.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public ChatService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    public String chatWithDocument(String prompt) {
        System.out.println(" PROMPT IS : "+prompt);
        var systemPromptTemplate = """
                You are a helpful assistant, conversing with a user about the subjects contained in a set of documents.
                Use the information from the DOCUMENTS section to provide accurate answers. If unsure or if the answer
                isn't found in the DOCUMENTS section, simply state that you don't know the answer and do not mention
                the DOCUMENTS section.
                
                DOCUMENTS:
                {documents}
                """;
        List<Document> similarDocs = vectorStore.similaritySearch(SearchRequest.query(prompt).withTopK(5));
        String content = similarDocs.stream().map(Document::getContent).collect(Collectors.joining(System.lineSeparator()));
        return chatClient.prompt()
                .system(systemSpec -> systemSpec.text(systemPromptTemplate).param("documents",content))
                .user(prompt)
                .call()
                .content();

    }
}
