package oystudy.clonecoding.product.entity

import jakarta.persistence.*
import oystudy.clonecoding.global.entity.BaseTimeEntity

@Entity
@Table(
    name = "products"
)
class Product(
    @Embedded
    val name: Name,

    @Embedded
    val price: Price,

    @Convert(converter = CategoryConverter::class)
    @Column(nullable = false)
    val category: Category

) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
