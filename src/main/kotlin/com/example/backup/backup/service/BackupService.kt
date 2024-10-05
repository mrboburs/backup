package com.example.backup.backup.service

import com.example.backup.dbconfig.repository.DbConfigRepository
import com.example.backup.dbconfig.service.DbConfigService
import com.example.backup.exception.EntityNotFoundException
import com.example.backup.exception.InactiveDatabaseException
import org.springframework.beans.factory.annotation.Value

import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
class BackupService(
    private val dbConfigService: DbConfigService,
    private val dbConfigRepository: DbConfigRepository,
    private var telegramBotService: TelegramBotService,
) {
    @Value("\${telegram.bot.chanel-id}")
    private val chanelid: String = ""

    @Value("\${telegram.bot.file}")
    private val folderPath: String = ""

    fun backupByActiveStatus(isActive: Boolean) {


        var dbConfigList = dbConfigService.findByActiveStatus(isActive)

        for (i in dbConfigList.indices) {
            if (dbConfigList[i].isActive == true) {
                backupDatabase(
                    port = dbConfigList[i].port.toString(),
                    host = dbConfigList[i].host,
                    dbUser = dbConfigList[i].dbUser,
                    dbName = dbConfigList[i].dbName,
                    dbPassword = dbConfigList[i].password,
                    backupFilePath = dbConfigList[i].backupFilePath,

                    )
            }

            telegramBotService.sendFilesInFolderToChannel(
                chanelid,
                folderPath

            )

        }
    }

    fun getBackupById(id: Long): String {
        val existingDbConfig = dbConfigRepository.findById(id)
            .orElseThrow { EntityNotFoundException() }
        if (!existingDbConfig.isActive) {
            throw InactiveDatabaseException()
        }
        backupDatabase(
            port = existingDbConfig.port.toString(),
            host = existingDbConfig.host,
            dbUser = existingDbConfig.dbUser,
            dbName = existingDbConfig.dbName,
            dbPassword = existingDbConfig.password,
            backupFilePath = existingDbConfig.backupFilePath,

            )

        return existingDbConfig.backupFilePath

    }

    fun runBackup() {


        var dbConfigList = dbConfigService.findAll()

        for (i in dbConfigList.indices) {
            if (dbConfigList[i].isActive == true) {
                backupDatabase(
                    port = dbConfigList[i].port.toString(),
                    host = dbConfigList[i].host,
                    dbUser = dbConfigList[i].dbUser,
                    dbName = dbConfigList[i].dbName,
                    dbPassword = dbConfigList[i].password,
                    backupFilePath = dbConfigList[i].backupFilePath,

                    )
            }

        }
    }

    fun start(
        databases: List<Map<String, String>>
    ) {

        databases.forEach { dbConfig ->
            backupDatabase(
                dbConfig["host"]!!,
                dbConfig["port"]!!,
                dbConfig["dbUser"]!!,
                dbConfig["dbPassword"]!!,
                dbConfig["dbName"]!!,
                dbConfig["backupFilePath"]!!
            )
        }


    }

    private fun backupDatabase(
        host: String,
        port: String,
        dbUser: String,
        dbPassword: String,
        dbName: String,
        backupFilePath: String
    ) {
        // Construct the pg_dump command
        val command = "pg_dump -h $host -p $port -U $dbUser -F c -b -v -f $backupFilePath $dbName"

        val processBuilder = ProcessBuilder("bash", "-c", command)

        // Set environment variable for password (used by pg_dump)
        processBuilder.environment()["PGPASSWORD"] = dbPassword

        try {
            // Start the process
            val process = processBuilder.start()

            // Capture the output
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                println(101)
                println(line)
                println(103)
            }

            // Wait for the process to complete
            val exitCode = process.waitFor()
            if (exitCode == 0) {
                println("Backup for $dbName created successfully at $backupFilePath")
            } else {
                println("Error occurred during backup of $dbName. Exit code: $exitCode")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

