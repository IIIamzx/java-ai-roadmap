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
@ConfigurationProperties(prefix = "pgvector")
public class PgVectorConfig {

    private String host;

    private int port;

    private String database;

    private String user;

    private String password;

    private String table;

}
