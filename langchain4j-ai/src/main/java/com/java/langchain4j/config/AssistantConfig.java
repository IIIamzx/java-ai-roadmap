package com.java.langchain4j.config;

import com.java.langchain4j.service.ChatAssistantService;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
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
    public ChatAssistantService init(){
        return AiServices.builder(ChatAssistantService.class)
                .chatLanguageModel(chatLanguageModel).build();
    }
}
