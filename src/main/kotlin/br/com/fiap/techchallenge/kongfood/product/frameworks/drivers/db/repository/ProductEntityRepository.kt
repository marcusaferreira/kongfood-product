package br.com.fiap.techchallenge.kongfood.product.frameworks.drivers.db.repository

import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductCategory
import br.com.fiap.techchallenge.kongfood.product.frameworks.drivers.db.orm.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductEntityRepository : JpaRepository<ProductEntity, String> {

    fun findByCategory(productCategory: ProductCategory): List<ProductEntity>
    fun findByName(name: String): ProductEntity?
}