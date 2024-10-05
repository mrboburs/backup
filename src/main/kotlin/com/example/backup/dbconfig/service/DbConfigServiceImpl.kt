package com.example.backup.dbconfig.service

import com.example.backup.dbconfig.dto.CreateDbConfigDto
import com.example.backup.dbconfig.dto.DbConfigDto
import com.example.backup.dbconfig.dto.UpdateDbConfigDto
import com.example.backup.dbconfig.model.DbConfig
import com.example.backup.dbconfig.repository.DbConfigRepository
import com.example.backup.exception.DuplicateEntityException

import com.example.backup.exception.EntityNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class DbConfigServiceImpl(
    private val dbConfigRepository: DbConfigRepository
) : DbConfigService {

    override  fun findByActiveStatus(isActive:Boolean): List<DbConfigDto> {
        return    return dbConfigRepository.findByActiveStatus(isActive).map { dbConfig ->
            DbConfigDto(
                id = dbConfig.id,
                dbUser = dbConfig.dbUser,
                dbName = dbConfig.dbName,
                password = dbConfig.password,
                host = dbConfig.host,
                port = dbConfig.port,
                backupFilePath = dbConfig.backupFilePath,
                isActive = dbConfig.isActive
            )
        }
    }

    override  fun findByDbName(query:String): List<DbConfigDto> {
        return    return dbConfigRepository.findByDbName(query).map { dbConfig ->
            DbConfigDto(
                id = dbConfig.id,
                dbUser = dbConfig.dbUser,
                dbName = dbConfig.dbName,
                password = dbConfig.password,
                host = dbConfig.host,
                port = dbConfig.port,
                backupFilePath = dbConfig.backupFilePath,
                isActive = dbConfig.isActive
            )
        }
    }


  override  fun getActiveDbConfigs(): List<DbConfigDto> {
        return    return dbConfigRepository.findAllActive().map { dbConfig ->
            DbConfigDto(
                id = dbConfig.id,
                dbUser = dbConfig.dbUser,
                dbName = dbConfig.dbName,
                password = dbConfig.password,
                host = dbConfig.host,
                port = dbConfig.port,
                backupFilePath = dbConfig.backupFilePath,
                isActive = dbConfig.isActive
            )
        }
    }

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
        try {

            val savedDbConfig = dbConfigRepository.save(dbconfig)
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
        } catch (ex: DataIntegrityViolationException) {
            throw DuplicateEntityException(
                )
        }

    }

    override fun deleteById(id: Long) {
        val existingDbConfig = dbConfigRepository.findById(id)
            .orElseThrow { EntityNotFoundException() }
        // Perform the delete operation
        dbConfigRepository.delete(existingDbConfig)
    }

   override  fun findAll(): List<DbConfigDto> {
       return dbConfigRepository.findAll().map { dbConfig ->
           DbConfigDto(
               id = dbConfig.id,
               dbUser = dbConfig.dbUser,
               dbName = dbConfig.dbName,
               password = dbConfig.password,
               host = dbConfig.host,
               port = dbConfig.port,
               backupFilePath = dbConfig.backupFilePath,
               isActive = dbConfig.isActive
           )
       }
    }


    override fun findById(id: Long): DbConfig {

       return dbConfigRepository.findById(id).orElseThrow { EntityNotFoundException() }
    }


   override fun updateDbConfigById(
        id: Long,
        dto: UpdateDbConfigDto
    ): DbConfigDto {
       val existingDbConfig = dbConfigRepository.findById(id)
           .orElseThrow { EntityNotFoundException() }

       // Update fields only if they are not null
       dto.dbUser?.let { existingDbConfig.dbUser = it }
       dto.dbName?.let { existingDbConfig.dbName = it }
       dto.password?.let { existingDbConfig.password = it }
       dto.host?.let { existingDbConfig.host = it }
       dto.port?.let { existingDbConfig.port = it }
       dto.backupFilePath?.let { existingDbConfig.backupFilePath = it }
       dto.isActive?.let { existingDbConfig.isActive = it }

       return try {
           dbConfigRepository.save(existingDbConfig).toDto()
       } catch (ex: DataIntegrityViolationException) {
           throw DuplicateEntityException()
       }

    }
    private fun DbConfig.toDto(): DbConfigDto = DbConfigDto(
        id = this.id,
        dbUser = this.dbUser,
        dbName = this.dbName,
        password = this.password,
        host = this.host,
        port = this.port,
        backupFilePath = this.backupFilePath,
        isActive = this.isActive
    )
    // Conversion logic (optional to keep as a separate function)


}

