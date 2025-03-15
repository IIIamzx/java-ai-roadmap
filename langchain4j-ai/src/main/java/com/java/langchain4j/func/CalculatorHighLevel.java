package com.java.langchain4j.func;

import dev.langchain4j.agent.tool.Tool;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class CalculatorHighLevel {

    public static final Logger logger = LoggerFactory.getLogger(CalculatorHighLevel.class);

    @Tool("计算两数之和，确保结果正确")
    private int sum(int a,int b){
        logger.info("a={},b={}, a+b={}",a,b,a+b);
        return a+b;
    }

    @Tool("计算两数之差，确保结果正确")
    private int sub(int a,int b){
        logger.info("a={},b={}, a-b={}",a,b,a-b);
        return a-b;
    }
}
