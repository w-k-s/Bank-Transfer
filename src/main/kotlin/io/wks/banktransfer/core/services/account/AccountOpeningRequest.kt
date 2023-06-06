package io.wks.banktransfer.core.services.account

import java.math.BigDecimal
import java.util.UUID

data class AccountOpeningRequest(val customerId: UUID, val openingBalance: BigDecimal)