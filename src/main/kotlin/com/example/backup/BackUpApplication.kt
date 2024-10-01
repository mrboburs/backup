package com.example.backup


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class BackUpApplication
//    (
//    private val telegramBotService: TelegramBotService
//) {
//    init {
//        try {
//            val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
//            botsApi.registerBot(telegramBotService)
//        } catch (e: TelegramApiRequestException) {
//            e.printStackTrace()
//        }
//    }
//}

fun main(args: Array<String>) {
	runApplication<BackUpApplication>(*args)


}
