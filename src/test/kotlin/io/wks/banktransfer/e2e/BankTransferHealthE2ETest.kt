package io.wks.banktransfer.e2e

import io.restassured.RestAssured
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus


class BankTransferHealthE2ETest : BaseE2ETest(){

    @Test
    fun contextLoads() {
        RestAssured
            .get("actuator/health")
            .then()
            .statusCode(HttpStatus.OK.value())
    }

}
