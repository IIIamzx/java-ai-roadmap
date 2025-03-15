package com.java.langchain4j.config;

import com.java.langchain4j.func.CalculatorHighLevel;
import com.java.langchain4j.service.ChatAssistantService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description:
 */
@Configuration
@RequiredArgsConstructor
public class AssistantConfig {

    final ChatLanguageModel chatLanguageModel;

    @Bean
    public ChatAssistantService init(EmbeddingStore<TextSegment> embeddingStore){
        return AiServices.builder(ChatAssistantService.class)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))//会话记忆
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))//增强检索
                .tools(new CalculatorHighLevel())
                .chatLanguageModel(chatLanguageModel).build();
    }
}
