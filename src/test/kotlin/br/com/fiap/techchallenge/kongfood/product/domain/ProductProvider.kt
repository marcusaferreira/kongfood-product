package br.com.fiap.techchallenge.kongfood.product.domain


import br.com.fiap.techchallenge.kongfood.product.domain.entities.Product
import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductCategory
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductRequestModel
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductResponseModel
import java.util.*

internal class ProductProvider {

    companion object{
        fun getProductDTO() = ProductRequestModel(
            id = null,
            name = "Hamburguer",
            description = "A delicious hamburguer",
            price = "10.0",
            category = "Main courses",
            status = true
        )

        fun getProduct(): Product {
            return Product(
                id = UUID.randomUUID(),
                name = "Hamburguer",
                description = "A delicious hamburguer",
                price = 10.0.toBigDecimal(),
                category = ProductCategory.MAIN_COURSES,
                status = true
            )

        }

        fun getProductResponse(): ProductResponseModel {
            return ProductResponseModel(
                id = UUID.randomUUID().toString(),
                name = "Hamburguer",
                description = "A delicious hamburguer",
                price = "10.0",
                category = ProductCategory.MAIN_COURSES.type,
                status = true
            )
        }
    }
}