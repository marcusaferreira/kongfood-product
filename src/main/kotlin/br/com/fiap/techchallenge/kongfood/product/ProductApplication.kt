package br.com.fiap.techchallenge.kongfood.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class ProductApplication

fun main(args: Array<String>) {
	runApplication<ProductApplication>(*args)
}
