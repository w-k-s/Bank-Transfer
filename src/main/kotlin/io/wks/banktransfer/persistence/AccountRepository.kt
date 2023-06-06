package io.wks.banktransfer.persistence

import io.wks.banktransfer.core.models.account.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : CrudRepository<Account, UUID>