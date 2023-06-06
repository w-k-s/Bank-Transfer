package io.wks.banktransfer.api.account

import java.math.BigDecimal
import java.util.UUID

data class AccountDetailResponse(val id: UUID, val balance: BigDecimal)