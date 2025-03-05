package com.java.ai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: zhangxin
 * @date: 2025/3/3
 * @description:
 */
@RestController
//@RequiredArgsConstructor
@RequestMapping("/ai")
public class ChatController {

    public static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    ChatClient chatClient;

    public ChatController(@Qualifier("chatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }



    @GetMapping("/chat")
    public String chat(@RequestParam("message") String msg){
        logger.info("用户输入：{}",msg);
        String content = chatClient
                .prompt()
                .user(msg)
                .call()
                .content();
        logger.info("模型回答：{}",content);
        return content;
    }
}
