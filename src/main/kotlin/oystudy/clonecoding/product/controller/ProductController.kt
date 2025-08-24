package oystudy.clonecoding.product.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import oystudy.clonecoding.product.service.ProductService
import oystudy.clonecoding.product.service.dto.CreateRequest
import oystudy.clonecoding.product.service.dto.UpdateRequest
import oystudy.clonecoding.product.service.dto.UpdateResponse
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

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: UpdateRequest): ResponseEntity<UpdateResponse> {
        val response = productService.update(id, request)
        return ResponseEntity.ok(response)
    }
}
