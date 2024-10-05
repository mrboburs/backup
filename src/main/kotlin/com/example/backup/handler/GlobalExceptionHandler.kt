package com.example.backup.handler

import com.example.backup.exception.DuplicateEntityException
import com.example.backup.exception.EntityNotFoundException
import com.example.backup.exception.InactiveDatabaseException
import com.example.backup.util.ResponseData

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {


    @ExceptionHandler(InactiveDatabaseException::class)
    fun handleInactiveDatabaseException(
        ex: InactiveDatabaseException
    ): ResponseEntity<ResponseData> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ResponseData(
                null,
                "Database  with this ID  is inactive.!",
                false))
    }


    @ExceptionHandler(DuplicateEntityException ::class)
    fun handleDuplicateEntityException(
        ex: DuplicateEntityException
    ): ResponseEntity<ResponseData> {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ResponseData(
                null,
                "Duplicate Entity entry",
                false
            ))
    }


    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(
        ex: EntityNotFoundException
    ): ResponseEntity<ResponseData> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ResponseData(
                null,
                "Entity not found!",
                false))
    }
}