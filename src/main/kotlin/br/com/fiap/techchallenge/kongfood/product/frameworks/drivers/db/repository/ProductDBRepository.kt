package br.com.fiap.techchallenge.kongfood.product.frameworks.drivers.db.repository

import br.com.fiap.techchallenge.kongfood.product.domain.entities.Product
import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductCategory
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.repository.ProductRepository
import br.com.fiap.techchallenge.kongfood.product.frameworks.drivers.db.orm.ProductEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductDBRepository(
    val productEntityRepository: ProductEntityRepository
) : ProductRepository {
    override fun save(product: Product) {
        val productEntity = ProductEntity(product)
        productEntityRepository.save(productEntity)
    }

    override fun findById(id: UUID): Optional<Product> {
        val productEntity = productEntityRepository.findById(id.toString())
        return if (productEntity.isPresent) {
            Optional.of(ProductEntity.toProduct(productEntity.get()))
        } else {
            Optional.empty()
        }
    }

    override fun findByCategory(productCategory: ProductCategory): List<Product> {
        val productEntities = productEntityRepository.findByCategory(productCategory)
        return productEntities.map { ProductEntity.toProduct(it) }
    }

    override fun findByName(name: String): Product? {
        val productEntity = productEntityRepository.findByName(name)
        return productEntity?.let { ProductEntity.toProduct(it) }
    }

    override fun findAll(): List<Product> {
        val productEntities = productEntityRepository.findAll()
        return productEntities.map { ProductEntity.toProduct(it) }
    }

    override fun existsByName(product: String): Boolean {
        return productEntityRepository.findByName(product) != null
    }

    override fun exists(product: Product): Boolean {
        return productEntityRepository.existsById(product.id.toString())
    }
}