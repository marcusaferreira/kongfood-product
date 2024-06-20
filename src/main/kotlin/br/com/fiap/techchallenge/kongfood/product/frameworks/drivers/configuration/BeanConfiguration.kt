package br.com.fiap.techchallenge.kongfood.product.frameworks.drivers.configuration

import br.com.fiap.techchallenge.kongfood.domain.product.usecases.ProductChangeStatusInteractor
import br.com.fiap.techchallenge.kongfood.product.ProductApplication
import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductFactoryImpl
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.controllers.DomainProductService
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.controllers.ProductService
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.presenters.ProductResponseFormatter
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.repository.ProductRepository
import br.com.fiap.techchallenge.kongfood.product.domain.usecases.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [ProductApplication::class])
class BeanConfiguration {

    @Bean
    fun productRegisterBoundary(productRepository: ProductRepository): ProductRegisterBoundary {
        val productFactory = ProductFactoryImpl()
        val productPresenter = ProductResponseFormatter()
        return ProductRegisterInteractor(
            productRepository = productRepository,
            productFactory = productFactory,
            productPresenter = productPresenter
        )
    }

    @Bean
    fun productSearchBoundary(productRepository: ProductRepository): ProductSearchBoundary {
        val productPresenter = ProductResponseFormatter()
        return ProductSearchInteractor(
            productRepository = productRepository,
            productPresenter = productPresenter
        )
    }

    @Bean
    fun productUpdateBoundary(
        productRepository: ProductRepository,
        productSearchBoundary: ProductSearchBoundary
    ): ProductUpdateBoundary {
        val productFactory = ProductFactoryImpl()
        val productPresenter = ProductResponseFormatter()
        return ProductUpdateInteractor(
            productRepository = productRepository,
            productFactory = productFactory,
            productPresenter = productPresenter,
            productSearchBoundary = productSearchBoundary
        )
    }

    @Bean
    fun productChangeStatusBoundary(
        productRepository: ProductRepository,
        productSearchBoundary: ProductSearchBoundary
    ): ProductChangeStatusBoundary {
        val productPresenter = ProductResponseFormatter()
        return ProductChangeStatusInteractor(
            productRepository = productRepository,
            productPresenter = productPresenter,
            productSearchBoundary = productSearchBoundary
        )
    }

    @Bean
    fun productService(
        productRepository: ProductRepository,
        productRegisterBoundary: ProductRegisterBoundary,
        productUpdateBoundary: ProductUpdateBoundary,
        productChangeStatusBoundary: ProductChangeStatusBoundary,
        productSearchBoundary: ProductSearchBoundary
    ): ProductService {
        return DomainProductService(
            productRegisterBoundary = productRegisterBoundary,
            productUpdateBoundary = productUpdateBoundary,
            productChangeStatusBoundary = productChangeStatusBoundary,
            productSearchBoundary = productSearchBoundary
        )
    }

}