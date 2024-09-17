package com.example.backup.service

import com.example.backup.entity.Product
import com.example.backup.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(private val productRepository: ProductRepository) {

    fun findAll(): List<Product> = productRepository.findAll()

    fun findById(id: Long): Product? = productRepository.findById(id).orElse(null)

    fun save(product: Product): Product = productRepository.save(product)

    fun deleteById(id: Long) = productRepository.deleteById(id)
}
