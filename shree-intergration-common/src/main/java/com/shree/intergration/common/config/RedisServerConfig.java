package com.shree.intergration.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "isport.redis")
@Data
public class RedisServerConfig {
    private Boolean clusterMode = false;
    private String[] clusterNodes = new String[]{};
    private String host = "127.0.0.1";
    private int port = 6379;
    private String password = "";
    private int database = 0;
    private RedisPoolConfig poolConfig = new RedisPoolConfig();
    private int connectTimeout = 3000;
    private int socketTimeout = 30;
    private int connectRetryTimes = 3;
}
