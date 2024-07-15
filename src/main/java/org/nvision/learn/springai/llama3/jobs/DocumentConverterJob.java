package org.nvision.learn.springai.llama3.jobs;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentConverterJob {
    private static final Logger logger = LoggerFactory.getLogger(DocumentConverterJob.class);

    private final VectorStore vectorStore;

    @Value("classpath:/documents/story1.md")
    Resource textFile1;

    @Value("classpath:/documents/story2.txt")
    Resource textFile2;

    public DocumentConverterJob(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadVectorStore() {
        logger.info("LOADING .md file as Document ****** ");
        TextReader tr1 = new TextReader(textFile1);
        tr1.getCustomMetadata().put("location","North Pole");
        tr1.setCharset(Charset.defaultCharset());
        List<Document> documents = new ArrayList<>(tr1.get());

        logger.info("LOADING TEXT file as Document ++++++");
        TextReader tr2 = new TextReader(textFile2);
        tr1.getCustomMetadata().put("location","Italy");
        tr1.setCharset(Charset.defaultCharset());
        //documents.addAll(tr2.get());

        logger.info("CREATING AND STORING Embeddings form Documents -----");
        vectorStore.add(documents);
        logger.info("DONE WITH CREATION OF EMBEDDINGS ******  -----");

    }
}
