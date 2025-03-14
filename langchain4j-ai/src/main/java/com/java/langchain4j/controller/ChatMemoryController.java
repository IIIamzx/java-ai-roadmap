package com.java.langchain4j.controller;

import com.java.langchain4j.service.ChatAssistantService;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
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
public class ChatMemoryController {

    public static final Logger logger = LoggerFactory.getLogger(ChatMemoryController.class);
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

    final ChatLanguageModel chatLanguageModel;
    final ChatAssistantService chatAssistantService;


    @GetMapping("/low/chat_memory")
    public String lowChatMemory(@RequestParam("message") String msg){
        logger.info("【用户输入】：{}",msg);
        chatMemory.add(UserMessage.from(msg));
        ChatResponse response = chatLanguageModel.chat(chatMemory.messages());
        String ans = response.aiMessage().text();
        logger.info("【模型回答】：{}",ans);
        chatMemory.add(response.aiMessage());
        return ans;
    }

    @GetMapping("/high/chat_memory")
    public String highChatMemory(@RequestParam("memoryId") String memoryId,@RequestParam("message") String msg){
        logger.info("【用户输入】：{}",msg);
        String ans = chatAssistantService.chat(memoryId, msg);
        logger.info("【模型回答】：{}",ans);
        return ans;
    }
}
