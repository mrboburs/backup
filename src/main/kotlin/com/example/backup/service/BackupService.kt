package com.example.backup.service

import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
@Service
class BackupService {

    fun start() {
        // List of databases with their configurations
        val databases = listOf(
            mapOf(
                "dbUser" to "mrb",
                "dbPassword" to "0224",
                "dbName" to "elcondb",
                "host" to "5.189.131.138",
                "port" to "5432",
                "backupFilePath" to "/home/bobur/test/backup/elcondb_backup.sql"
            ),
            mapOf(
                "dbUser" to "postgres",
                "dbPassword" to "1996",
                "dbName" to "test",
                "host" to "localhost",
                "port" to "5432",
                "backupFilePath" to "/home/bobur/test/backup/newdb_backup.sql"
            )
        )

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

//@Service
//class BackupService {
//
//    fun start() {
//        // Database credentials
//        val dbUser = "mrb"
//        val dbPassword = "0224"
//        val dbName = "elcondb"
//        val host = "5.189.131.138" // or the IP address of the PostgreSQL server
//        val port = "5432" // default PostgreSQL port
//
//        // Output file path
//        val backupFilePath = "/home/bobur/test/backup.sql"
//
//        // Construct the pg_dump command
//        val command = "pg_dump -h $host -p $port -U $dbUser -F c -b -v -f $backupFilePath $dbName"
//
//        val processBuilder = ProcessBuilder("bash", "-c", command)
//
//        // Set environment variable for password (used by pg_dump)
//        processBuilder.environment()["PGPASSWORD"] = dbPassword
//
//        try {
//            // Start the process
//            val process = processBuilder.start()
//
//            // Capture the output
//            val reader = BufferedReader(InputStreamReader(process.inputStream))
//            var line: String?
//            while (reader.readLine().also { line = it } != null) {
//                println(line)
//            }
//
//            // Wait for the process to complete
//            val exitCode = process.waitFor()
//            if (exitCode == 0) {
//                println("Backup created successfully at $backupFilePath")
//            } else {
//                println("Error occurred during backup. Exit code: $exitCode")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}
