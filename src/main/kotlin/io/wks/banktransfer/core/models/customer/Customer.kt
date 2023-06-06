package io.wks.banktransfer.core.models.customer

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("customer", schema = "bank_transfer")
data class Customer(
    @Id
    var id: UUID? = null,
    val name: String
)
