package com.java.langchain4j.controller;

import com.alibaba.fastjson.JSONObject;
import com.java.langchain4j.service.ChatAssistantService;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description: 函数调用
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/langchain4j")
public class FunctionCallingController {

    public static final Logger logger = LoggerFactory.getLogger(FunctionCallingController.class);

    final ChatLanguageModel chatLanguageModel;
    final ChatAssistantService chatAssistantService;


    @GetMapping("/low/func")
    public String lowChatMemory(@RequestParam("message") String msg){
        logger.info("【用户输入】：{}",msg);

        ToolSpecification toolSpecification = ToolSpecification.builder()
                .name("Calculator")
                .description("输入两个数，计算两个数的和")
                .parameters(JsonObjectSchema.builder()
                        .addIntegerProperty("a", "第一个参数")
                        .addIntegerProperty("b", "第二个参数")
                        .required("a", "b").build())
                .build();

        ChatResponse chatResponse = chatLanguageModel.doChat(ChatRequest.builder()
                .messages(List.of(UserMessage.from(msg)))
                .parameters(ChatRequestParameters.builder().toolSpecifications(toolSpecification).build()).build());

        chatResponse.aiMessage().toolExecutionRequests().forEach(toolExecutionRequest -> {

            System.out.println(toolExecutionRequest.name());
            System.out.println(toolExecutionRequest.arguments());

            try {
                Class<?> aClass = Class.forName("com.java.langchain4j.func." + toolExecutionRequest.name());
                Runnable runnable = (Runnable) JSONObject.parseObject(toolExecutionRequest.arguments(), aClass);
                runnable.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        logger.info("【模型回答】：{}",chatResponse.aiMessage().text());
        return "func success";
    }

//    @GetMapping("/high/func")
//    public String highChatMemory(@RequestParam("message") String msg){
//        logger.info("【用户输入】：{}",msg);
//        String ans = chatAssistantService.chat(msg);
//        logger.info("【模型回答】：{}",ans);
//        return ans;
//    }


}
