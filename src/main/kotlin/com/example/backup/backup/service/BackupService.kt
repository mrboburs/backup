package com.example.backup.backup.service

interface BackupService {

    fun backupByActiveStatus(isActive: Boolean)
    fun getBackupById(id: Long): String
    fun runBackup()
    fun start(
        databases: List<Map<String, String>>
    )
}