package com.java.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * @author: zhangxin
 * @date: 2025/3/12
 * @description:
 */
public class FuncService implements Function<FuncService.Request,FuncService.Response> {

    public static final Logger logger = LoggerFactory.getLogger(FuncService.class);

    @Override
    public Response apply(Request request) {
        logger.info("{} is taken off !", request.who);
        return new Response(10);
    }

    public record Request(String who) {

    }

    public record Response(int days) {

    }
}
