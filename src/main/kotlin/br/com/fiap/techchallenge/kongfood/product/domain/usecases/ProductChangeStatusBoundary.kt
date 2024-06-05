package br.com.fiap.techchallenge.kongfood.product.domain.usecases

import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductResponseModel
import java.util.*

fun interface ProductChangeStatusBoundary {
    fun changeStatus(productID: UUID) : ProductResponseModel
}
