package io.wks.banktransfer.app.account

import io.wks.banktransfer.api.account.AccountDetailResponse
import io.wks.banktransfer.api.account.AccountOpeningRequest
import io.wks.banktransfer.api.account.AccountApi
import io.wks.banktransfer.core.models.account.Account
import io.wks.banktransfer.core.services.account.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.*
import io.wks.banktransfer.core.services.account.AccountOpeningRequest as AccountOpeningServiceRequest

@RestController
class AccountController(private val accountService: AccountService) : AccountApi {

    override fun openAccount(request: AccountOpeningRequest): ResponseEntity<AccountDetailResponse> {
        val account = accountService.openAccount(
            AccountOpeningServiceRequest(
                customerId = request.customerId,
                openingBalance = request.openingBalance
            )
        )
        return ResponseEntity.ok(account.toAccountDetailResponse())
    }

    override fun getAccount(accountId: UUID): ResponseEntity<AccountDetailResponse> {
        val account = accountService.getAccount(accountId)
        return ResponseEntity.ok(account.toAccountDetailResponse())
    }

    private fun Account.toAccountDetailResponse(): AccountDetailResponse {
        return AccountDetailResponse(
            id = requireNotNull(id) { "UNEXPECTED ERROR: Account created without id" },
            balance = balance
        )
    }
}