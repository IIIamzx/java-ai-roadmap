package com.java.langchain4j.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
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
    
    final PgVectorConfig pgVectorConfig;

//    @Bean
//    public EmbeddingStore<TextSegment> embeddingStoreInit(){
//        return new InMemoryEmbeddingStore<>();
//    }
    

    @Bean
    public EmbeddingStore<TextSegment> initEmbeddingStore() {
        return PgVectorEmbeddingStore.builder()
                .table(pgVectorConfig.getTable())
                .dropTableFirst(true)
                .createTable(true)
                .host(pgVectorConfig.getHost())
                .port(pgVectorConfig.getPort())
                .user(pgVectorConfig.getUser())
                .password(pgVectorConfig.getPassword())
                .dimension(384)
                .database(pgVectorConfig.getDatabase())
                .build();
    }

}
