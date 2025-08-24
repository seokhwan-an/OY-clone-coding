package oystudy.clonecoding.product.entity

import jakarta.persistence.*
import oystudy.clonecoding.global.entity.BaseTimeEntity

@Entity
@Table(
    name = "product"
)
class Product(
    @Embedded
    val name: Name,

    @Embedded
    val price: Price,

    @Convert(converter = CategoryConverter::class)
    @Column(nullable = false)
    var category: Category

) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun update(name: String, price: Int, category: String) {
        this.name.update(name)
        this.price.update(price)
        this.category = Category.findByValue(category)
    }
}
