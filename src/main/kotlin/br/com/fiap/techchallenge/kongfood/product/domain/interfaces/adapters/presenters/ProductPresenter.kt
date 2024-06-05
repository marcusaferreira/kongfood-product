package br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.presenters

import br.com.fiap.techchallenge.kongfood.product.domain.entities.Product
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductResponseModel

interface ProductPresenter {

    fun prepareSuccessResponse(product: Product): ProductResponseModel

    fun prepareFailureResponse(error: String)
    fun prepareAllSuccessResponse(products: List<Product>): List<ProductResponseModel>
}