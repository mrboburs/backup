package com.example.backup.backup.controller



import com.example.backup.backup.service.BackupService
import com.example.backup.backup.service.TelegramBotService
import com.example.backup.backup.dto.BackupRequest
import com.example.backup.util.ResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/backup")
 class BackupController(
    private val backupService: BackupService,
    private var telegramBotService: TelegramBotService,

    ) {

    // Get an dbConfig by ID
    @GetMapping("/is-active/{isActive}")
    fun backupByActiveStatus(
        @PathVariable isActive: Boolean
    ): ResponseEntity<ResponseData> {
        val res = backupService.backupByActiveStatus(isActive)
        return   ResponseEntity.ok(
            ResponseData(
                res,
            )
        )
    }

    // Get an dbConfig by ID
     @GetMapping("/{id}")
     fun getDbConfigById(
       @PathVariable id: Long
    ): ResponseEntity<ResponseData> {
        val res = backupService.getBackupById(id)
        return   ResponseEntity.ok(
            ResponseData(
            res,
        )
        )
    }

    @PostMapping("/start")
    fun startBackup(@RequestBody backupRequest: BackupRequest): ResponseEntity<String> {

        return try {
            // Convert the list of DatabaseConfig objects to a list of maps
            val databases = backupRequest.databases.map {
                mapOf(
                    "dbUser" to it.dbUser,
                    "dbPassword" to it.dbPassword,
                    "dbName" to it.dbName,
                    "host" to it.host,
                    "port" to it.port,
                    "backupFilePath" to it.backupFilePath
                )
            }

       println(databases)
            // Pass the list to the backupService
            backupService.start(databases)
            telegramBotService.sendFilesInFolderToChannel(
                "@myjavachan",
                "/home/bobur/test/backup"
            )
            ResponseEntity.ok("Backup started successfully.")

        } catch (e: Exception) {
            ResponseEntity.status(500).body("An error occurred while starting the backup: ${e.message}")
        }

        // Handle the message

    }

}
