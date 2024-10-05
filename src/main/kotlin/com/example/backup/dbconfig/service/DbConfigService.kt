package com.example.backup.dbconfig.service

import com.example.backup.dbconfig.dto.CreateDbConfigDto
import com.example.backup.dbconfig.dto.DbConfigDto
import com.example.backup.dbconfig.dto.UpdateDbConfigDto
import com.example.backup.dbconfig.model.DbConfig



interface DbConfigService {

    fun findByActiveStatus(isActive:Boolean): List<DbConfigDto>
    fun findByDbName(query:String): List<DbConfigDto>

    fun getActiveDbConfigs(): List<DbConfigDto>

    fun createDbConfig(createDbConfigDto: CreateDbConfigDto): CreateDbConfigDto

    fun findAll(): List<DbConfigDto>


    fun deleteById(id: Long)

    fun findById(id: Long): DbConfig

    fun updateDbConfigById(
        id: Long, updatedDbConfig: UpdateDbConfigDto
    ): DbConfigDto

}