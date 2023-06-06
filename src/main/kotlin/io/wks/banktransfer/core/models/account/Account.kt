package io.wks.banktransfer.core.models.account

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.*

@Table("account", schema = "bank_transfer")
data class Account(
    @Id var id: UUID? = null,
    val customerId: UUID,
    val balance: BigDecimal
)
