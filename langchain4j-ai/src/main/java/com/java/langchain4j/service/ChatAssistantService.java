package com.java.langchain4j.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description:
 */
public interface ChatAssistantService {

//    @SystemMessage("假如你是特朗普，接下来你必须以特朗普的语气来进行对话")
    String chat(String message);

    String chat(@MemoryId String memoryId, @UserMessage String msg);
}
