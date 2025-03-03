package com.java.ai.init;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
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
public class ChatClientInit {

    final MoonshotChatModel moonshotModel;

    @Bean
    public ChatClient chatClient(){
        return ChatClient.builder(moonshotModel).build();
    }


}
