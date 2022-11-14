package com.eric.data.mappers

import com.eric.data.dto.ProductListDTO
import konveyor.base.randomBuild
import org.junit.Test

class ProductMapperTest {

    @Test
    fun mapToDomain() {
        val sut: ProductListDTO = randomBuild()
        val result = sut.mapToDomain()
        result.productDomainModels.map { product ->
            val productItem = sut.results.find { it.name == product.name }
            productItem?.let {
                assert(it.designer.name == product.designer)
                assert(it.price.formattedValue == product.formattedPrice)
            }
        }
    }
}