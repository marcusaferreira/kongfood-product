package br.com.fiap.techchallenge.kongfood.product.frameworks.drivers.db.orm

import br.com.fiap.techchallenge.kongfood.product.domain.entities.Product
import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductCategory
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: String,
    @Column(name = "price", nullable = false)
    val price: BigDecimal,
    @Column(name = "name", nullable = false, unique = true)
    val name: String,
    @Column(name = "description", nullable = false)
    val description: String,
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    val category: ProductCategory,
    @Column(name = "status", nullable = false)
    val status: Boolean
) {

    constructor(product: Product) : this(
        product.id.toString(),
        product.price,
        product.name,
        product.description,
        product.category,
        product.status
    )

    companion object {
        fun toProduct(productEntity: ProductEntity): Product {
            return Product(
                UUID.fromString(productEntity.id),
                productEntity.price,
                productEntity.name,
                productEntity.description,
                productEntity.category,
                productEntity.status
            )
        }
    }

}