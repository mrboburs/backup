package com.example.backup.config

import com.example.backup.service.TelegramBotService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.File

@Component
class StartupRunner(
    private val telegramBotService: TelegramBotService

) : CommandLineRunner {
    @Value("\${telegram.bot.chanel-id}") private val chanelid: String=""
    @Value("\${telegram.bot.file}") private val filePath: String=""
    override fun run(vararg args: String?) {

        val file = File(filePath)

        // Handle the message
        telegramBotService.sendFileToChannel(chanelid,
            file,
            "")


    }
}
