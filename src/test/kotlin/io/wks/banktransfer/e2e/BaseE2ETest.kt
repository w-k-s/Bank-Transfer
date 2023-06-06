package io.wks.banktransfer.e2e

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import io.restassured.RestAssured
import io.restassured.parsing.Parser
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.web.util.UriComponentsBuilder
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseE2ETest {

    val APP_SERVICE = "app"
    val APP_PORT = 8080

    val DB_SERVICE = "postgres"
    val DB_PORT = 5432

    val objectWriter = ObjectMapper()

    val dockerCompose: KDockerComposeContainer by lazy {
        defineDockerCompose()
            .withExposedService(APP_SERVICE, APP_PORT, Wait.forListeningPort())
            .withExposedService(DB_SERVICE, DB_PORT, Wait.forListeningPort())
    }

    class KDockerComposeContainer(file: File) : DockerComposeContainer<KDockerComposeContainer>(file)

    private fun defineDockerCompose() = KDockerComposeContainer(File("src/test/resources/compose.yaml"))

    @BeforeAll
    fun beforeAll() {
        dockerCompose.start()
    }

    @BeforeEach
    fun setup(){
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = dockerCompose.getServicePort(APP_SERVICE, APP_PORT)
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterEach
    fun tearDown(){
        RestAssured.reset()
    }

    @AfterAll
    fun afterAll() {
        dockerCompose.stop()
    }
}