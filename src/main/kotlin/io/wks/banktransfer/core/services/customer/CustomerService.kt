package io.wks.banktransfer.core.services.customer

import io.wks.banktransfer.core.models.customer.Customer
import io.wks.banktransfer.core.services.exceptions.NotFoundException
import io.wks.banktransfer.persistence.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun createCustomer(request: CreateCustomerRequest) =
        customerRepository.save(Customer(name = request.name))

    fun getCustomer(customerId: UUID) = customerRepository.findById(customerId)
        .orElseThrow { throw NotFoundException("Customer with id '$customerId' not found") }
}