package com.java.langchain4j.config;

import dev.langchain4j.web.search.searchapi.SearchApiWebSearchEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description: 联网搜索能力
 */
@Configuration
@RequiredArgsConstructor
public class WebSearchConfig {
    
    final SearchConfig searchConfig;

    @Bean
    public SearchApiWebSearchEngine initWebSearch() {
        return SearchApiWebSearchEngine.builder()
                .apiKey(searchConfig.getApiKey())
                .engine(searchConfig.getEngine())
                .build();
    }

}
