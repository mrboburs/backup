package com.example.backup.config

import com.example.backup.service.BackupService
import com.example.backup.service.TelegramBotService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.File

@Component
class StartupRunner(
    private var telegramBotService: TelegramBotService,
    private var backupService: BackupService

) : CommandLineRunner {

    @Value("\${telegram.bot.chanel-id}")
    private val chanelid: String = ""

    @Value("\${telegram.bot.file}")
    private val folderPath: String = ""
    override fun run(vararg args: String?) {
        backupService.start()
//        val file = File(folderPath)

        // Handle the message
        telegramBotService.sendFilesInFolderToChannel(
            chanelid,
            folderPath,

            )


    }
}
