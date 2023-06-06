package io.wks.banktransfer.core.services.account

import io.wks.banktransfer.core.models.account.Account
import io.wks.banktransfer.core.services.exceptions.NotFoundException
import io.wks.banktransfer.persistence.AccountRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(private val accountRepository: AccountRepository) {

    fun openAccount(request: AccountOpeningRequest) =
        accountRepository.save(
            Account(
                customerId = request.customerId,
                balance = request.openingBalance
            )
        )

    fun getAccount(accountId: UUID) = accountRepository.findById(accountId)
        .orElseThrow { NotFoundException("Account with id '$accountId' not found") }
}