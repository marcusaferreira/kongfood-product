package br.com.fiap.techchallenge.kongfood.product.domain.entities

import br.com.fiap.techchallenge.kongfood.product.domain.entities.Product
import java.util.*

fun interface ProductFactory {

    fun create(productID: UUID?, name: String, description: String, price: String, category: String): Product
}