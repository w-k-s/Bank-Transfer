package io.wks.banktransfer.persistence

import io.wks.banktransfer.core.models.customer.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : CrudRepository<Customer, UUID>