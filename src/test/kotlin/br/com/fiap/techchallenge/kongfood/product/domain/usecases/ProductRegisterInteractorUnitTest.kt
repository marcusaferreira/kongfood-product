package br.com.fiap.techchallenge.kongfood.product.domain.usecases

import br.com.fiap.techchallenge.kongfood.product.domain.DomainException
import br.com.fiap.techchallenge.kongfood.product.ProductApplicationTests
import br.com.fiap.techchallenge.kongfood.product.domain.ProductProvider
import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductFactory
import br.com.fiap.techchallenge.kongfood.product.domain.entities.ProductFactoryImpl
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.presenters.ProductResponseFormatter
import br.com.fiap.techchallenge.kongfood.product.domain.interfaces.adapters.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [ProductApplicationTests::class])
class ProductRegisterInteractorUnitTest {

    private final val productRepository: ProductRepository = mock()
    private final val productFactory: ProductFactory = ProductFactoryImpl()
    private final val productPresenter = ProductResponseFormatter()
    val productRegisterInterecator = ProductRegisterInteractor(productRepository, productFactory, productPresenter)

    @Test
    fun `should add product`() {
        `when`(productRepository.existsByName("Hamburguer")).thenReturn(false)

        val result = productRegisterInterecator.registerProduct(ProductProvider.getProductDTO())

        Assertions.assertNotNull(result)
        verify(productRepository, times(1)).existsByName("Hamburguer")
        verify(productRepository, times(1)).save(any())
    }

    @Test
    fun `should throw exception when try to add product with same name`() {
        `when`(productRepository.existsByName("Hamburguer")).thenReturn(true)

        Assertions.assertThrows(DomainException::class.java) {
            productRegisterInterecator.registerProduct(ProductProvider.getProductDTO())
        }
        verify(productRepository, times(1)).existsByName("Hamburguer")
        verify(productRepository, times(0)).save(any())
    }
}