package io.wks.banktransfer.app.customer

import io.wks.banktransfer.api.customer.CreateCustomerRequest
import io.wks.banktransfer.api.customer.CustomerApi
import io.wks.banktransfer.api.customer.CustomerDetailResponse
import io.wks.banktransfer.core.models.customer.Customer
import io.wks.banktransfer.core.services.customer.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*
import io.wks.banktransfer.core.services.customer.CreateCustomerRequest as CreateCustomerServiceRequest

@RestController
class CustomerController(private val customerService: CustomerService) : CustomerApi {

    override fun createCustomer(request: CreateCustomerRequest): ResponseEntity<CustomerDetailResponse> {
        val customer = customerService.createCustomer(
            CreateCustomerServiceRequest(
                name = request.name
            )
        )
        return ResponseEntity.ok(customer.toCustomerDetailResponse())
    }

    override fun getCustomer(customerId: UUID): ResponseEntity<CustomerDetailResponse> {
        val customer = customerService.getCustomer(customerId)
        return ResponseEntity.ok(customer.toCustomerDetailResponse())
    }

    private fun Customer.toCustomerDetailResponse(): CustomerDetailResponse {
        return CustomerDetailResponse(
            id = requireNotNull(id) { "UNEXPECTED ERROR: Customer created without id" },
            name = name
        )
    }
}