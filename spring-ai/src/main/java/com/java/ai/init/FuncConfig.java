package com.java.ai.init;

import com.java.ai.service.FuncService;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangxin
 * @date: 2025/3/12
 * @description:
 */
@Configuration
public class FuncConfig {

    @Bean
    public FunctionCallback askForLeaveCallBack(){
        return FunctionCallback.builder()
                .function("askForLeave", new FuncService())
                .description("当有人请假时，返回请假天数")
                .inputType(FuncService.Request.class)
                .build();
    }

}
