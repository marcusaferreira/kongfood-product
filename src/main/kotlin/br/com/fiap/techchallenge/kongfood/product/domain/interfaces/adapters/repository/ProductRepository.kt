package br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.repository

import br.com.fiap.techchallenge.kongfood.product.domain.entities.Product
import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductCategory
import java.util.*

interface ProductRepository {
    fun save(product: Product)
    fun findById(id: UUID): Optional<Product>
    fun findByCategory(productCategory: ProductCategory): List<Product>
    fun findByName(name: String): Product?
    fun findAll(): List<Product>
    fun existsByName(product: String): Boolean
    fun exists(product: Product): Boolean
}