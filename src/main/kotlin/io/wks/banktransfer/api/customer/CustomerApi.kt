package io.wks.banktransfer.api.customer

import io.wks.banktransfer.api.account.AccountOpeningRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RequestMapping(
    "/api/customer/v1",
//    consumes = [MediaType.APPLICATION_JSON_VALUE],
//    produces = [MediaType.APPLICATION_JSON_VALUE],
)
interface CustomerApi {

    @PostMapping("/")
    fun createCustomer(@RequestBody request: CreateCustomerRequest) : ResponseEntity<CustomerDetailResponse>

    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable("customerId") customerId: UUID) : ResponseEntity<CustomerDetailResponse>
}