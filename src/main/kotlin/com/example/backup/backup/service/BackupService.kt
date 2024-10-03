package com.example.backup.backup.service

import com.example.backup.dbconfig.service.DbConfigServiceImpl
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
@Service
class BackupService(
    private val dbConfigService: DbConfigServiceImpl
) {
fun runBackup(){
    var dbConfigList = dbConfigService.findAll()

    for (i in dbConfigList.indices){
        if (dbConfigList[i].isActive==true){
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
                println(line)
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

