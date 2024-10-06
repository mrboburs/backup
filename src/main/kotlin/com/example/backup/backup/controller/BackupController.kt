package com.example.backup.backup.controller

import com.example.backup.backup.dto.BackupRequest
import com.example.backup.util.ResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/backup")
interface BackupController {
    // Get an dbConfig by ID
    @GetMapping("/is-active")
    fun backupByActiveStatus(
        @RequestParam isActive: Boolean
    ): ResponseEntity<ResponseData>


    // Get an dbConfig by ID
    @GetMapping("/{id}")
    fun getBackupById(
        @PathVariable id: Long
    ): ResponseEntity<ResponseData>


    @PostMapping("/start")
    fun startBackup(
        @RequestBody backupRequest: BackupRequest
    ): ResponseEntity<String>
}