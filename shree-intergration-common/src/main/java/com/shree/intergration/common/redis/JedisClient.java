package com.shree.intergration.common.redis;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shree.intergration.common.base.SpringContext;
import com.shree.intergration.common.base.singleton.AbstractSingleton;
import com.shree.intergration.common.config.RedisServerConfig;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

@Log4j2
public class JedisClient {

    private JedisCluster jedisCluster = null;
    private JedisPool jedisPool = null;
    @Getter
    private RedisServerConfig config;

    public static final AbstractSingleton<JedisClient> HOLDER = new AbstractSingleton<JedisClient>() {
        protected JedisClient newInstance() {
            return new JedisClient();
        }
    };

    public static JedisClient getInstance() {
        return HOLDER.getInstance();
    }

    public static Jedis getJedisInstance() {
        return HOLDER.getInstance().getJedis();
    }

    public static JedisCluster getClusterInstance() {
        return HOLDER.getInstance().getJedisCluster();
    }

    public static JedisCommands getCommandsInstance() {
        return HOLDER.getInstance().getJedisCommands();
    }

    private JedisClient() {
        try {
            config = SpringContext.getBean(RedisServerConfig.class);
            // 初始化Jedis池设定
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(config.getPoolConfig().getMaxIdle());
            jedisPoolConfig.setMaxTotal(config.getPoolConfig().getMaxTotal());
            jedisPoolConfig.setMinIdle(config.getPoolConfig().getMinIdle());
            jedisPoolConfig.setMaxWaitMillis(config.getPoolConfig().getMaxWaitMillis());
            jedisPoolConfig.setTestOnBorrow(config.getPoolConfig().isTestOnBorrow());
            jedisPoolConfig.setBlockWhenExhausted(config.getPoolConfig().isBlockWhenExhausted());
            if (config.getClusterMode()) {
                // 集群模式连接
                Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
                for (String hostAndPort : config.getClusterNodes()) {
                    String[] nodeConfig = hostAndPort.split(":");
                    hostAndPortsSet.add(new HostAndPort(nodeConfig[0], Integer.parseInt(nodeConfig[1])));
                }
                // 初始化集群模式Jedis池
                if (StringUtils.isNotBlank(config.getPassword())) {
                    this.jedisCluster = new JedisCluster(hostAndPortsSet, config.getConnectTimeout(), config.getSocketTimeout(), config.getConnectRetryTimes(), config.getPassword(), jedisPoolConfig);
                } else {
                    this.jedisCluster = new JedisCluster(hostAndPortsSet, config.getConnectTimeout(), config.getSocketTimeout(), config.getConnectRetryTimes(), null, jedisPoolConfig);
                }
            } else {
                // 单节点模式连接
                if (StringUtils.isNotBlank(config.getPassword())) {
                    this.jedisPool = new JedisPool(jedisPoolConfig, config.getHost(), config.getPort(), config.getConnectTimeout(), config.getPassword(), config.getDatabase());
                } else {
                    this.jedisPool = new JedisPool(jedisPoolConfig, config.getHost(), config.getPort(), config.getConnectTimeout(), null, config.getDatabase());
                }
            }
        } catch (Exception ex) {
            log.error("Jedis初始化错误！", ex);
        }
    }

    public Jedis getJedis() {
        if (null != jedisPool) {
            try {
                return jedisPool.getResource();
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public JedisCommands getJedisCommands() {
        if (config.getClusterMode()) {
            return getJedisCluster();
        } else {
            return getJedis();
        }
    }
}
