package io.wks.banktransfer.e2e

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.path.json.JsonPath
import io.wks.banktransfer.api.customer.CreateCustomerRequest
import io.wks.banktransfer.core.services.account.AccountOpeningRequest
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import java.math.BigDecimal
import java.util.UUID

class AccountApiE2ETest : BaseE2ETest() {

    @Test
    fun `GIVEN customer WHEN creating account THEN account created`() {
        // GIVEN
        val name = "Jack Torrence"
        val balance = BigDecimal.TEN
        val customerId = createCustomer(name).getUUID("id")

        // WHEN
        val accountResponse = createAccount(customerId, balance)

        // THEN
        assertEquals(balance.toString(), accountResponse.get<Any?>("balance").toString())
    }

    @Test
    fun `GIVEN account created WHEN retrieving account by id THEN account details retrieved`() {
        // GIVEN
        val name = "Jack Torrence"
        val balance = BigDecimal.TEN
        val customerId = createCustomer(name).getUUID("id")
        val accountId = createAccount(customerId, balance).getUUID("id")

        // WHEN
        val accountResponse = getAccount(accountId)

        // THEN
        assertEquals(balance.toString(), accountResponse.get<Any?>("balance").toString())
    }

    private fun createCustomer(name: String): JsonPath{
        return RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(CreateCustomerRequest(name))
            .`when`()
                .post("/api/customer/v1/")
            .then()
                .statusCode(HttpStatus.OK.value())
            .extract()
                .jsonPath()
    }

    private fun createAccount(customerId: UUID, balance: BigDecimal): JsonPath{
        return RestAssured
            .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(AccountOpeningRequest(customerId = customerId, balance))
            .`when`()
                .post("/api/account/v1/")
            .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
            .extract()
                .jsonPath()
    }

    private fun getAccount(accountId: UUID): JsonPath{
        return RestAssured
            .given()
                .log().all()
                .contentType(ContentType.JSON)
            .`when`()
                .get("/api/account/v1/${accountId}")
            .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
            .extract()
                .jsonPath()
    }
}