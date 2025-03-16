package com.java.langchain4j.controller;

import com.java.langchain4j.service.ChatAssistantService;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description: 多模态
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/langchain4j")
public class MultipartController {

    public static final Logger logger = LoggerFactory.getLogger(MultipartController.class);

    final ChatLanguageModel chatLanguageModel;
    final ChatAssistantService chatAssistantService;

    @GetMapping("/low/multi")
    public String highChat(@RequestParam("message") String msg) throws IOException {
        logger.info("【用户输入】：{}",msg);

//        Image img = ImageIO.read(new File("/Users/zhangxin/code/java-ai-roadmap/langchain4j-ai/src/main/resources/doc/cat.png"));

        File file = new File("/Users/zhangxin/code/java-ai-roadmap/langchain4j-ai/src/main/resources/doc/cat.png");
        byte[] files = Files.readAllBytes(file.toPath());
        UserMessage userMessage = UserMessage.from(TextContent.from(msg),ImageContent.from(Base64.getEncoder().encodeToString(files),"image/png"));
        ChatResponse chatResponse = chatLanguageModel.chat(List.of(userMessage));
        logger.info("【模型回答】：{}",chatResponse.aiMessage().text());
        return chatResponse.aiMessage().text();
    }

}
