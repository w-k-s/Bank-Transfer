package io.wks.banktransfer.api.account

import java.math.BigDecimal
import java.util.*

data class AccountOpeningRequest(
    val customerId: UUID,
    val openingBalance: BigDecimal = BigDecimal.ZERO
)