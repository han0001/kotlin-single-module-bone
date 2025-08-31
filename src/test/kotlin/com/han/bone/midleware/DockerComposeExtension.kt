package com.han.bone.midleware

import com.han.bone.util.logging.log
import io.kotest.core.listeners.AfterProjectListener
import io.kotest.core.listeners.BeforeProjectListener
import io.kotest.core.spec.AutoScan
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.ShellStrategy
import java.io.File

@AutoScan
class DockerComposeExtension : BeforeProjectListener, AfterProjectListener {

    private val container = DockerComposeContainer<Nothing>(File("src/test/resources/docker-compose.yml")).apply {
//        waitingFor("mysql_for_test", ShellStrategy().withCommand("mysql -u'test' -p'123456' -e'select 1' && sleep 2")) // 접속 가능해질때까지 대기
        waitingFor("redis_for_test", ShellStrategy().withCommand("redis-cli get test"))
    }

    override suspend fun beforeProject() {
        log.info { "[DOCKER_COMPOSE_EXTENSION] start" }
        container.start()
    }

    override suspend fun afterProject() {
        container.stop()
        log.info { "[DOCKER_COMPOSE_EXTENSION] stop" }
    }
}