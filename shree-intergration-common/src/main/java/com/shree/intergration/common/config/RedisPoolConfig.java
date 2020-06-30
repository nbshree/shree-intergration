package com.shree.intergration.common.config;

import lombok.Data;

@Data
public class RedisPoolConfig {
    // 最小空闲连接数
    private int minIdle = 5;
    // 最大空闲连接数
    private int maxIdle = 10;
    // 最大连接数
    private int maxTotal = 100;
    // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常
    // 如果小于零:阻塞不确定的时间,  默认-1
    private int maxWaitMillis = 5000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private boolean testOnBorrow = true;
    // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
    private boolean blockWhenExhausted = true;
}
