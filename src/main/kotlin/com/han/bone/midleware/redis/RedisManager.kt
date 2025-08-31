package com.han.bone.midleware.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisManager(
    private val redisTemplate: StringRedisTemplate,
) {

    fun writeToRedis(key: String, value: String) {
        redisTemplate.opsForValue().set(key, value, 30, TimeUnit.MINUTES)
    }

    fun readFromRedis(key: String): String? {
        return redisTemplate.opsForValue().get(key)
    }

}