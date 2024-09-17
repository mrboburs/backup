package com.example.backup.controller

import com.example.backup.entity.Product
import com.example.backup.service.ProductService

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/products")
class ProductController @Autowired constructor(private val productService: ProductService) {

    // Create a new product
    @PostMapping
    fun createProduct(@Valid @RequestBody product: Product): ResponseEntity<Any> {
        val createdProduct = productService.save(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct)
    }

    // Get all products
    @GetMapping
    fun getAllProducts(): ResponseEntity<Any> {
        val products = productService.findAll()
        return ResponseEntity.ok(products)
    }

    // Get a product by ID
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Any> {
        val product = productService.findById(id)
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            val errorBody = mapOf("error" to "Product with ID $id not found")
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody)
        }
    }

    // Update a product by ID
    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @Valid @RequestBody updatedProduct: Product
    ): ResponseEntity<Any> {
        val existingProduct = productService.findById(id)
        return if (existingProduct != null) {
            val productToUpdate = existingProduct.copy(name = updatedProduct.name, price = updatedProduct.price)
            ResponseEntity.ok(productService.save(productToUpdate))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        val product = productService.findById(id)
        return if (product != null) {
            productService.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
