package com.example.backup.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.InputFile
import java.io.File

@Service
class TelegramBotService : TelegramLongPollingBot() {

    @Value("\${telegram.bot.username}") private val username: String=""
    @Value("\${telegram.bot.token}") private val token: String=""

    override fun getBotToken(): String {
        return token
    }

    override fun getBotUsername(): String {
        return username
    }

    override fun onUpdateReceived(update: Update) {
        println(28)


//            val chatId = update.message.chatId
//            println(chatId)
            val file = File("/home/bobur/test/backup.sql")
            // Handle the message
            sendFileToChannel("@myjavachan",
                file,
                "")

    }

    fun sendFileToChannel(channelId: String, file: File, caption: String? = null) {
        val inputFile = InputFile(file)
        val sendDocument = SendDocument()
        sendDocument.chatId = channelId
        sendDocument.document = inputFile
        caption?.let { sendDocument.caption = it }

        try {
            execute(sendDocument)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
    }
}

