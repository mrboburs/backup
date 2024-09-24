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


    }
    fun sendFilesInFolderToChannel(channelId: String, folderPath: String) {
        val folder = File(folderPath)
        if (folder.exists() && folder.isDirectory) {
            folder.listFiles()?.filter { it.isFile }?.forEach { file ->
                sendFileToChannel(channelId, file)
            }
        } else {
            println("The folder $folderPath does not exist or is not a directory.")
        }
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

