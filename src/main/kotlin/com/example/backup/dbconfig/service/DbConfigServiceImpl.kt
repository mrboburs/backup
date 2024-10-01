package com.example.backup.dbconfig.service

import com.example.backup.dbconfig.dto.CreateDbConfigDto
import com.example.backup.dbconfig.dto.UpdateDbConfigDto
import com.example.backup.dbconfig.model.DbConfig



interface DbConfigServiceImpl {
    fun createDbConfig(createDbConfigDto: CreateDbConfigDto): CreateDbConfigDto

    fun findAll(): List<DbConfig>

    fun deleteById(id: Long)

    fun findById(id: Long): DbConfig?

    fun updateDbConfigById(
        id: Long, updatedDbConfig: UpdateDbConfigDto
    ): DbConfig?

}