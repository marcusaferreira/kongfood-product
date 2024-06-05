package br.com.fiap.techchallenge.kongfood.product.domain.usecases

import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductRequestModel
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductResponseModel

fun interface ProductUpdateBoundary {
    fun update(product: ProductRequestModel): ProductResponseModel
}