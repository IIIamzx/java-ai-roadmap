package com.java.langchain4j.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangxin
 * @date: 2025/3/13
 * @description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "search")
public class SearchConfig {

    private String apiKey;

    private String engine;

}
