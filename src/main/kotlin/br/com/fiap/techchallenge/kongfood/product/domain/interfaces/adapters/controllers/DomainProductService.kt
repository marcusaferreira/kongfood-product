package br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.controllers

import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductRequestModel
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.models.ProductResponseModel
import br.com.fiap.techchallenge.kongfood.product.domain.usecases.ProductChangeStatusBoundary
import br.com.fiap.techchallenge.kongfood.product.domain.usecases.ProductRegisterBoundary
import br.com.fiap.techchallenge.kongfood.product.domain.usecases.ProductSearchBoundary
import br.com.fiap.techchallenge.kongfood.product.domain.usecases.ProductUpdateBoundary
import java.util.*

class DomainProductService(
    val productRegisterBoundary: ProductRegisterBoundary,
    val productUpdateBoundary: ProductUpdateBoundary,
    val productChangeStatusBoundary: ProductChangeStatusBoundary,
    val productSearchBoundary: ProductSearchBoundary
) : ProductService {
    override fun addProduct(product: ProductRequestModel): String {
        return productRegisterBoundary.registerProduct(product).id!!
    }

    override fun updateProduct(product: ProductRequestModel): ProductResponseModel {
        return productUpdateBoundary.update(product)
    }

    override fun changeStatus(productID: UUID) {
        productChangeStatusBoundary.changeStatus(productID)
    }

    override fun findProductById(productID: UUID): ProductResponseModel {
        return productSearchBoundary.findById(productID)
    }

    override fun findProductByCategory(category: String): List<ProductResponseModel> {
        return productSearchBoundary.findByCategory(category)
    }

    override fun findAll(): List<ProductResponseModel> {
        return productSearchBoundary.findAll()
    }

}