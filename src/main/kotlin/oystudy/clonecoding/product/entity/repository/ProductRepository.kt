package oystudy.clonecoding.product.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import oystudy.clonecoding.product.entity.Product

interface ProductRepository : JpaRepository<Product, Long> {

}
