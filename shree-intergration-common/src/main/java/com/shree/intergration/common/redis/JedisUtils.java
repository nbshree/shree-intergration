package com.shree.intergration.common.redis;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shree.intergration.common.config.RedisServerConfig;
import com.shree.intergration.common.util.JacksonUtils;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * Jedis便捷工具帮助类
 * 覆盖String类型以及Hash类型的常用方法
 * 所有方法名均与原生Jedis一样
 * 具体使用请参看Jedis客户端说明
 *
 * @author Darkprayer
 */
@Log4j2
public class JedisUtils {

    private static RedisServerConfig redisConfig = JedisClient.getInstance().getConfig();

    private static final String ERROR_LOG = "JedisUtils执行{}命令时发生错误！";

    public static Jedis getJedisInstance() {
        return JedisClient.getJedisInstance();
    }

    public static JedisCluster getClusterInstance() {
        return JedisClient.getClusterInstance();
    }

    public static String set(String key, String value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.set(key, value);
            } catch (Exception ex) {
                log.error(StringUtils.format(ERROR_LOG, "set"), ex);
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.set(key, value);
            } catch (Exception ex) {
                log.error(StringUtils.format(ERROR_LOG, "set"), ex);
                return null;
            }
        }
    }

    public static <T> String set(String key, T value) {
        String data = JacksonUtils.getJson(value);
        if (StringUtils.isNotEmpty(data)) {
            return set(key, data);
        } else {
            return null;
        }
    }

    public static String get(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.get(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.get(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> T get(String key, Class<T> clazz) {
        String data = get(key);
        if (!StringUtils.isEmpty(data)) {
            return JacksonUtils.parseJson(data, clazz);
        } else {
            return null;
        }
    }

    public static Boolean exists(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.exists(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.exists(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long exists(String... keys) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.exists(keys);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.exists(keys);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long persist(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.persist(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.persist(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long del(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.del(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.del(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long del(String... keys) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.del(keys);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.del(keys);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static String type(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.type(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.type(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long expire(String key, int seconds) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.expire(key, seconds);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.expire(key, seconds);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long pexpire(String key, long milliseconds) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.pexpire(key, milliseconds);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.pexpire(key, milliseconds);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long expireAt(String key, long unixTime) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.expireAt(key, unixTime);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.expireAt(key, unixTime);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long pexpireAt(String key, long millisecondsTimestamp) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.pexpireAt(key, millisecondsTimestamp);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.pexpireAt(key, millisecondsTimestamp);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long ttl(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.ttl(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.ttl(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long pttl(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.pttl(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.pttl(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static String getSet(String key, String value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.getSet(key, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.getSet(key, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> String getSet(String key, T value) {
        String data = JacksonUtils.getJson(value);
        if (StringUtils.isNotEmpty(data)) {
            return getSet(key, data);
        } else {
            return null;
        }
    }

    public static List<String> mget(String... keys) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.mget(keys);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.mget(keys);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long setnx(String key, String value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.setnx(key, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.setnx(key, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static String setex(String key, int expire, String value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.setex(key, expire, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.setex(key, expire, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> String setex(String key, int expire, T value) {
        String data = JacksonUtils.getJson(value);
        if (StringUtils.isNotEmpty(data)) {
            return setex(key, expire, data);
        } else {
            return null;
        }
    }

    public static String psetex(String key, long milliseconds, String value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.psetex(key, milliseconds, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.psetex(key, milliseconds, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> String psetex(String key, long milliseconds, T value) {
        String data = JacksonUtils.getJson(value);
        if (StringUtils.isNotEmpty(data)) {
            return psetex(key, milliseconds, data);
        } else {
            return null;
        }
    }

    public static String mset(String... keysvalues) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.mset(keysvalues);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.mset(keysvalues);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long msetnx(String... keysvalues) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.msetnx(keysvalues);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.msetnx(keysvalues);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long decrBy(String key, long decrement) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.decrBy(key, decrement);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.decrBy(key, decrement);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long decr(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.decr(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.decr(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long incrBy(String key, long increment) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.incrBy(key, increment);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.incrBy(key, increment);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Double incrByFloat(String key, double increment) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.incrByFloat(key, increment);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.incrByFloat(key, increment);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long incr(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.incr(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.incr(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long append(String key, String value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.append(key, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.append(key, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static String substr(String key, int start, int end) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.substr(key, start, end);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.substr(key, start, end);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long hset(String key, String field, String value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hset(key, field, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hset(key, field, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> Long hset(String key, String field, T value) {
        String data = JacksonUtils.getJson(value);
        if (StringUtils.isNotEmpty(data)) {
            return hset(key, field, data);
        } else {
            return null;
        }
    }

    public static String hget(String key, String field) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hget(key, field);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hget(key, field);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> T hget(String key, String field, Class<T> clazz) {
        String data = hget(key, field);
        if (!StringUtils.isEmpty(data)) {
            return JacksonUtils.parseJson(data, clazz);
        } else {
            return null;
        }
    }

    public static Long hsetnx(String key, String field, String value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hsetnx(key, field, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hsetnx(key, field, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static String hmset(String key, Map<String, String> hash) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hmset(key, hash);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hmset(key, hash);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static List<String> hmget(String key, String... fields) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hmget(key, fields);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hmget(key, fields);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> List<T> hmget(String key, Class<T> clazz, String... fields) {
        List<String> dataList = hmget(key, fields);
        if (null != dataList) {
            List<T> result = new ArrayList<>();
            for (String data : dataList) {
                T t = JacksonUtils.parseJson(data, clazz);
                if (null != t) {
                    result.add(t);
                }
            }
            return result;
        } else {
            return null;
        }
    }

    public static Long hincrBy(String key, String field, long value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hincrBy(key, field, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hincrBy(key, field, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Double hincrByFloat(String key, String field, double value) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hincrByFloat(key, field, value);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hincrByFloat(key, field, value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Boolean hexists(String key, String field) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hexists(key, field);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hexists(key, field);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long hdel(String key, String... fields) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hdel(key, fields);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hdel(key, fields);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long hdel(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hdel(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hdel(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Long hlen(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hlen(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hlen(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Set<String> hkeys(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hkeys(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hkeys(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static List<String> hvals(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hvals(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hvals(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> List<T> hvals(String key, Class<T> clazz) {
        List<String> dataList = hvals(key);
        if (null != dataList) {
            List<T> result = new ArrayList<>();
            for (String data : dataList) {
                T t = JacksonUtils.parseJson(data, clazz);
                if (null != t) {
                    result.add(t);
                }
            }
            return result;
        } else {
            return null;
        }
    }

    public static Map<String, String> hgetAll(String key) {
        if (redisConfig.getClusterMode()) {
            try (JedisCluster cluster = getClusterInstance()) {
                return cluster.hgetAll(key);
            } catch (Exception ex) {
                return null;
            }
        } else {
            try (Jedis jedis = getJedisInstance()) {
                return jedis.hgetAll(key);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static <T> Map<String, T> hgetAll(String key, Class<T> clazz) {
        Map<String, String> dataMap = hgetAll(key);
        if (null != dataMap) {
            Map<String, T> result = new HashMap<String, T>();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                T t = JacksonUtils.parseJson(entry.getValue(), clazz);
                if (null != t) {
                    result.put(entry.getKey(), t);
                }
            }
            return result;
        } else {
            return null;
        }
    }
}
