package io.wks.banktransfer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = [
    "io.wks.banktransfer.api",
    "io.wks.banktransfer.app",
    "io.wks.banktransfer.core.services",
    "io.wks.banktransfer.persistence"
])
class BankTransferApplication

fun main(args: Array<String>) {
    runApplication<BankTransferApplication>(*args)
}
