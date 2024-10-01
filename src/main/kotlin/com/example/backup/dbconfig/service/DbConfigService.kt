package com.example.backup.dbconfig.service

import com.example.backup.dbconfig.dto.CreateDbConfigDto
import com.example.backup.dbconfig.dto.UpdateDbConfigDto
import com.example.backup.dbconfig.model.DbConfig
import com.example.backup.dbconfig.repository.DbConfigRepository
import org.springframework.stereotype.Service

@Service
class DbConfigService(
    private val dbconfigRepository: DbConfigRepository
) : DbConfigServiceImpl {


    override fun createDbConfig(createDbConfigDto: CreateDbConfigDto): CreateDbConfigDto {
        val dbconfig = DbConfig(
            dbUser = createDbConfigDto.dbUser,
            dbName = createDbConfigDto.dbName,
            password = createDbConfigDto.password,
            host = createDbConfigDto.host,
            port = createDbConfigDto.port,
            backupFilePath = createDbConfigDto.backupFilePath,
            isActive = createDbConfigDto.isActive,
            )

        val savedDbConfig = dbconfigRepository.save(dbconfig)
        return CreateDbConfigDto(
            id = savedDbConfig.id,
            dbUser = savedDbConfig.dbUser,
            dbName = savedDbConfig.dbName,
            password = savedDbConfig.password,
            host = savedDbConfig.host,
            port = savedDbConfig.port,
            backupFilePath = savedDbConfig.backupFilePath,
            isActive = savedDbConfig.isActive,
        )
    }

    override fun deleteById(id: Long) = dbconfigRepository.deleteById(id)

    override fun findAll(): List<DbConfig> = dbconfigRepository.findAll()

    override fun findById(id: Long): DbConfig? = dbconfigRepository.findById(id).orElse(null)

   override fun updateDbConfigById(
        id: Long,
        updatedDbConfig: UpdateDbConfigDto
    ): DbConfig? {
        val existingDbConfig = findById(id)
        return if (existingDbConfig != null) {
            val dbConfigToUpdate = existingDbConfig.copy(
                dbUser = updatedDbConfig.dbUser.takeIf { it.isNotBlank() } ?: existingDbConfig.dbUser,
                dbName = updatedDbConfig.dbName.takeIf { it.isNotBlank() } ?: existingDbConfig.dbName,
                password = updatedDbConfig.password.takeIf { it.isNotBlank() } ?: existingDbConfig.password,
                host = updatedDbConfig.host.takeIf { it.isNotBlank() } ?: existingDbConfig.host,
                port = if (updatedDbConfig.port > 0) updatedDbConfig.port else existingDbConfig.port,
                backupFilePath = updatedDbConfig.backupFilePath.takeIf { it.isNotBlank() } ?: existingDbConfig.backupFilePath,
                isActive = updatedDbConfig.isActive // This can be updated as provided
            )
            dbconfigRepository.save(dbConfigToUpdate)
        } else {
            null
        }
    }

}

