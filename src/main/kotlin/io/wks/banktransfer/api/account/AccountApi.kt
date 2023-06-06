package io.wks.banktransfer.api.account

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@RequestMapping(
    "/api/account/v1",
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
interface AccountApi {

    @PostMapping("/")
    fun openAccount(@RequestBody request: AccountOpeningRequest): ResponseEntity<AccountDetailResponse>

    @GetMapping("/{accountId}")
    fun getAccount(@PathVariable("accountId") accountId: UUID): ResponseEntity<AccountDetailResponse>
}