package com.han.bone.midleware.redis
import com.han.bone.midleware.DockerComposeExtension
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RedisManagerTest @Autowired constructor(
    private val redisManager: RedisManager
) : FunSpec({

    extension(DockerComposeExtension())

    test("RedisManager Test") {
        val key = "test-key"
        val value = "test-value"

        redisManager.writeToRedis(key, value)
        redisManager.readFromRedis(key) shouldBe value
    }


})