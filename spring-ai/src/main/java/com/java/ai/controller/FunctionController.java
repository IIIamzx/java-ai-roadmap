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
public class FunctionController {
    public static final Logger logger = LoggerFactory.getLogger(FunctionController.class);

    ChatClient chatClient;

    public FunctionController(@Qualifier("chatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/funcCall")
    public String funcCall(@RequestParam("message") String msg){
        logger.info("用户输入：{}",msg);
        return chatClient.prompt(msg)
                .functions("askForLeave")
                .call()
                .content();
    }
}
