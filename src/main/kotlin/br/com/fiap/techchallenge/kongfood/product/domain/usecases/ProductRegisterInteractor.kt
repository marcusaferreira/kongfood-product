package br.com.fiap.techchallenge.kongfood.product.domain.usecases

import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductFactory
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductRequestModel
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.repository.ProductRepository
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductResponseModel
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.presenters.ProductPresenter
import br.com.fiap.techchallenge.kongfood.product.domain.usecases.ProductRegisterBoundary

class ProductRegisterInteractor(
    val productRepository: ProductRepository,
    val productFactory: ProductFactory,
    val productPresenter: ProductPresenter
) : ProductRegisterBoundary {

    override fun registerProduct(productRequestModel: ProductRequestModel): ProductResponseModel {
        if(productRepository.existsByName(productRequestModel.name)){
            productPresenter.prepareFailureResponse("Product already exists")
        }

        val newProduct = productFactory.create(
            null,
            productRequestModel.name,
            productRequestModel.description,
            productRequestModel.price,
            productRequestModel.category
        )
        productRepository.save(newProduct)
        return productPresenter.prepareSuccessResponse(newProduct)
    }
}