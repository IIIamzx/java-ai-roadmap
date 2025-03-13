package com.java.langchain4j.controller;

import com.java.langchain4j.service.ChatAssistantService;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description:
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/langchain4j")
public class ChatController {

    public static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    final ChatLanguageModel chatLanguageModel;
    final ChatAssistantService chatAssistantService;

    @GetMapping("/low/chat")
    public String lowChat(@RequestParam("message") String msg){
        logger.info("【用户输入】：{}",msg);
        String ans = chatLanguageModel.chat(UserMessage.from(msg)).aiMessage().text();
        logger.info("【模型回答】：{}",ans);
        return ans;
    }
    @GetMapping("/high/chat")
    public String highChat(@RequestParam("message") String msg){
        logger.info("【用户输入】：{}",msg);
        String ans = chatAssistantService.chat(msg);
        logger.info("【模型回答】：{}",ans);
        return ans;
    }
}
