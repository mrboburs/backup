package com.example.backup


import com.example.backup.service.TelegramBotService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


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
