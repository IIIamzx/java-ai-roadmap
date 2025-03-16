package com.java.langchain4j.controller;

import com.java.langchain4j.func.Person;
import com.java.langchain4j.service.ChatAssistantService;
import com.java.langchain4j.service.PersonService;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
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
 * @description: 结构化输出
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/langchain4j")
public class StructureController {

    public static final Logger logger = LoggerFactory.getLogger(StructureController.class);

    final ChatLanguageModel chatLanguageModel;
    final ChatAssistantService chatAssistantService;

    @GetMapping("/low/stru")
    public String highChat(@RequestParam("message") String msg){
        logger.info("【用户输入】：{}",msg);
        ResponseFormat responseFormat = ResponseFormat.builder()
                .type(ResponseFormatType.JSON)
                .jsonSchema(JsonSchema.builder()
                        .rootElement(JsonObjectSchema.builder()
                                .addIntegerProperty("age", "年龄")
                                .addIntegerProperty("weight", "体重")
                                .required("age", "weight")
                                .build())
                        .build())
                .build();

        ChatResponse chatResponse = chatLanguageModel.chat(ChatRequest.builder()
                .messages(List.of(UserMessage.from(msg)))
                .parameters(ChatRequestParameters.builder()
                        .responseFormat(responseFormat).build()).build());

        logger.info("【模型回答】：{}",chatResponse.aiMessage().text());
        return chatResponse.aiMessage().text();
    }
    @GetMapping("/high/stru")
    public String lowChat(@RequestParam("message") String msg){
        logger.info("【用户输入】：{}",msg);
        PersonService personService = AiServices.create(PersonService.class, chatLanguageModel);
        Person person = personService.extractPerson(msg);
        logger.info("【模型回答】：{}",person);
        return person.toString();
    }
}
