package br.com.fiap.techchallenge.kongfood.product

import br.com.kongfood.product.ProductApplication
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [ProductApplication::class])
class ProductApplicationTests {

	@Test
	fun contextLoads() {
	}

}
