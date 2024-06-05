package br.com.fiap.techchallenge.kongfood.product.domain.entities

import java.util.*

class ProductFactoryImpl : ProductFactory {
    override fun create(productID: UUID?, name: String, description: String, price: String, category: String): Product {
        return Product(
            productID ?: UUID.randomUUID(),
            price.toBigDecimal(),
            name,
            description,
            ProductCategory.getEnumByString(category)!!,
            true
        )
    }
}