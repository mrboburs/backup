package com.example.backup.dbconfig.controller

import com.example.backup.dbconfig.dto.CreateDbConfigDto
import com.example.backup.dbconfig.dto.UpdateDbConfigDto
import com.example.backup.dbconfig.service.DbConfigService
import com.example.backup.util.ResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class DbConfigControllerImpl(
    private val dbConfigService: DbConfigService
) : DbConfigController {

    // Get allActive dbConfig
    override fun getActiveDbConfigs(
    ): ResponseEntity<ResponseData> {
        val dbConfigs = dbConfigService.getActiveDbConfigs()
        return ResponseEntity.ok(ResponseData(dbConfigs))
    }


    // Create a new dbConfig
    override fun createDbConfig(
        dbConfig: CreateDbConfigDto,
    ): ResponseEntity<ResponseData> {
        var res =dbConfigService.createDbConfig(dbConfig)
        return ResponseEntity.ok(ResponseData(
            data = res,
            ))
    }


    //     Update an article by ID
    override fun updateDbConfigById(
        id: Long,
         dto: UpdateDbConfigDto
    ): ResponseEntity<ResponseData> {
        var res= dbConfigService.updateDbConfigById(id, dto)
        return ResponseEntity.ok(ResponseData(res))
    }


    // Delete an dbConfig by ID
    override fun deleteDbConfigById(
        id: Long
    ): ResponseEntity<ResponseData> {
        val dbConfig = dbConfigService.deleteById(id)
        return  ResponseEntity.ok(ResponseData(
            dbConfig,
        ))
    }

    // Get an dbConfigs by dbname
    override fun findByDbName(
        query: String
    ): ResponseEntity<ResponseData> {
        println(query)
        val dbConfig = dbConfigService.findByDbName(query)
        return   ResponseEntity.ok(ResponseData(
            dbConfig,
        ))
    }


    // Get an dbConfig by ID
    override fun getDbConfigById(
        id: Long
    ): ResponseEntity<ResponseData> {
        val dbConfig = dbConfigService.findById(id)
         return   ResponseEntity.ok(ResponseData(
              dbConfig,
         ))
    }


    // Get all dbConfig
    override fun getAllDbConfigs(
    ): ResponseEntity<ResponseData> {
        val dbConfigs = dbConfigService.findAll()
        return ResponseEntity.ok(ResponseData(dbConfigs))
    }


}
