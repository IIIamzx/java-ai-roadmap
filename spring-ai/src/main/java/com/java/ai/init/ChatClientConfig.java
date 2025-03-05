package com.java.ai.init;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangxin
 * @date: 2025/3/3
 * @description:
 */
@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    final MoonshotChatModel moonshotModel;

    @Bean
    public ChatClient chatClient(){
        return ChatClient.builder(moonshotModel)
                .build();
    }
    @Bean
    public ChatClient chatClientWithRole(){
        return ChatClient.builder(moonshotModel)
                .defaultSystem("你的身份是美国总统特朗普，回答请用特朗普的语气和知识背景进行回答")
                .build();
    }

    @Bean
    public ChatMemory chatMemory(){
        return new InMemoryChatMemory();
    }




}
