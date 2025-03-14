package com.java.langchain4j.controller;

import com.java.langchain4j.service.ChatAssistantService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description:
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/langchain4j")
public class RagController {

    public static final Logger logger = LoggerFactory.getLogger(RagController.class);

    final ChatLanguageModel chatLanguageModel;
    final ChatAssistantService chatAssistantService;
    final EmbeddingStore<TextSegment> embeddingStore;


//    @GetMapping("/low/rag")
//    public String lowChatMemory(@RequestParam("message") String msg){
//        logger.info("【用户输入】：{}",msg);
//        logger.info("【模型回答】：{}",msg);
//        return null;
//    }

    @GetMapping("/high/rag")
    public String highChatMemory(@RequestParam("message") String msg){
        logger.info("【用户输入】：{}",msg);
        String ans = chatAssistantService.chat(msg);
        logger.info("【模型回答】：{}",ans);
        return ans;
    }

    @GetMapping("/load")
    public String loadFile(){
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("/Users/zhangxin/code/java-ai-roadmap/langchain4j-ai/src/main/resources/doc");
        EmbeddingStoreIngestor.ingest(documents,embeddingStore);
        return "success";
    }
}
