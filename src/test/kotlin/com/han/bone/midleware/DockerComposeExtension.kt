package com.han.bone.midleware

import com.han.bone.util.logging.log
import io.kotest.core.listeners.AfterSpecListener
import io.kotest.core.listeners.BeforeSpecListener
import io.kotest.core.spec.Spec
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.ShellStrategy
import java.io.File


class DockerComposeExtension : BeforeSpecListener, AfterSpecListener {

    private val container = DockerComposeContainer<Nothing>(File("src/test/resources/docker-compose.yml")).apply {
        waitingFor("redis_for_test", ShellStrategy().withCommand("redis-cli get test"))
    }

    override suspend fun beforeSpec(spec: Spec) {
        log.info { "[DOCKER_COMPOSE_EXTENSION] start" }
        container.start()
    }

    override suspend fun afterSpec(spec: Spec) {
        container.stop()
        log.info { "[DOCKER_COMPOSE_EXTENSION] stop" }
    }
}