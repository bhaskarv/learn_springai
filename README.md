## Learn Spring AI
Spring AI provides abstractions to intereact seamlessly with various AI models. 
### Setup
  - llama3:latest running locally using ollama
  - Nomic embed text for text embedding generations - Lighter than llama3 text embeddings and runs faster

### Chat Model using Llama3 
### Embeddings generatipon using Llama3, Nomic embedding text
### RAG example using Local Vector store
  - Generate embeddings of local files
      - Use Tika Reader and Text Reader
      - Generate embeddings using Nomic text embedding model
      - Store the text embeddings in local files using SimpleVectorStore
      - Retrieve matching vectors using similarity search functionality provided by Embedding Model
  
    
