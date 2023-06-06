package io.wks.banktransfer.e2e

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.wks.banktransfer.api.customer.CreateCustomerRequest
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext

class CustomerApiE2ETest : BaseE2ETest() {

    @Test
    fun `GIVEN name WHEN creating customer THEN customer created`() {
        // GIVEN
        val name = "Jack Torrence"
        val request = CreateCustomerRequest(name)

        // WHEN
        RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(request)
            .`when`()
                .post("/api/customer/v1/")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(name))
    }

    @Test
    fun `GIVEN customer created WHEN retrieving customer by id THEN customer details retrieved`() {
        // GIVEN
        val name = "Alfred Hitchcock"

        val id = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body(CreateCustomerRequest(name))
            .`when`()
                .post("/api/customer/v1/")
            .then()
                .statusCode(HttpStatus.OK.value())
            .extract()
                .path<String>("id")

        RestAssured
            .`when`()
                .get("/api/customer/v1/${id}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(name))
    }
}