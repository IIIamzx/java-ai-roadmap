package com.java.langchain4j.func;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class Calculator implements Runnable {

    public static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    private int a;

    private int b;

    @Override
    public void run() {
        logger.info("a={},b={}, a+b={}",a,b,a+b);
    }
}
