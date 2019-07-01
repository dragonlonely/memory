package com.dragon.memory.commons.redis;

import redis.clients.jedis.JedisCluster;

/**
 * @author Administrator
 * @Date 2018/11/29 17:29
 * @Classname JedisClientCluster
 */
public class JedisClientCluster implements JedisClient{

    private JedisCluster jedisCluster;

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }

    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    public Long del(String key) {
        return jedisCluster.del(key);
    }


}
