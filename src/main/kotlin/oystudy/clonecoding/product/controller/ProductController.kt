package oystudy.clonecoding.product.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import oystudy.clonecoding.product.service.ProductService
import oystudy.clonecoding.product.service.dto.CreateRequest
import java.net.URI

@RequestMapping("/admin/products")
@RestController
class ProductController(
    private val productService: ProductService
) {

    @PostMapping
    fun create(@RequestBody request: CreateRequest): ResponseEntity<Void> {
        val create = productService.create(request)
        return ResponseEntity.created(URI("/admin/products/$create"))
            .build()
    }
}
