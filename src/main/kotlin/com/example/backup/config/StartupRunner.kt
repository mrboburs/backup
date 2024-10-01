package com.example.backup.config

import com.example.backup.backup.service.BackupService
import com.example.backup.backup.service.TelegramBotService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class StartupRunner(
    private var telegramBotService: TelegramBotService,
    private var backupService: BackupService

) : CommandLineRunner {

    @Value("\${telegram.bot.chanel-id}")
    private val chanelid: String = ""

    @Value("\${telegram.bot.file}") private val folderPath: String = ""
    override fun run(vararg args: String?) {
//        backupService.runBackup()
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                // Handle the message
//        telegramBotService.sendFilesInFolderToChannel(
//            chanelid,
//            folderPath
//
//            )
                println("Task executed at: ${System.currentTimeMillis()}")
            }
        }, 1000000000000, 2000) // Delay of 1 second, then repeat every 2 seconds

        println(26)



    }
}
