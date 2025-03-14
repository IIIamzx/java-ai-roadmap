package com.java.langchain4j.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
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
public class EmbeddingStoreConfig {

    @Bean
    public EmbeddingStore<TextSegment> embeddingStoreInit(){
        return new InMemoryEmbeddingStore<>();
    }

}
