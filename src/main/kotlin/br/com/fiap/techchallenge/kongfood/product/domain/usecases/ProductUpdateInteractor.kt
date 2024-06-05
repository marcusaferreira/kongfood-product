package br.com.fiap.techchallenge.kongfood.product.domain.usecases

import br.com.fiap.techchallenge.kongfood.product.domain.entities.Product
import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductFactory
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductRequestModel
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductResponseModel
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.presenters.ProductPresenter
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.repository.ProductRepository
import br.com.fiap.techchallenge.kongfood.product.domain.usecases.ProductSearchBoundary
import br.com.fiap.techchallenge.kongfood.product.domain.usecases.ProductUpdateBoundary
import java.util.*

class ProductUpdateInteractor(
    val productRepository: ProductRepository,
    val productFactory: ProductFactory,
    val productPresenter: ProductPresenter,
    val productSearchBoundary: ProductSearchBoundary
): ProductUpdateBoundary {
    override fun update(product: ProductRequestModel): ProductResponseModel {
        val productID = UUID.fromString(product.id!!)
        val productToUpdate = productFactory.create(
            productID,
            product.name,
            product.description,
            product.price,
            product.category
        )
        val savedProduct = productRepository.findById(productID)

        productSearchBoundary.validateExists(savedProduct, productID)
        validateAnotherProductExistByName(productToUpdate)
        validateProductIsSameCategory(savedProduct, productToUpdate)
        productRepository.save(productToUpdate)

        return productPresenter.prepareSuccessResponse(productToUpdate)
    }

    private fun validateAnotherProductExistByName(product: Product) {
        if (productRepository.exists(product)) {
            productPresenter.prepareFailureResponse("Product already exists")
        }
    }

    private fun validateProductIsSameCategory(
        savedProduct: Optional<Product>,
        productToUpdate: Product
    ) {
        if (savedProduct.get().category != productToUpdate.category) {
            productPresenter.prepareFailureResponse("Product category can't be changed")
        }
    }
}