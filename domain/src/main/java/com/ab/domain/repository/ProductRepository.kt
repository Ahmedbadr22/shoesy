package com.ab.domain.repository

import com.ab.domain.model.data.Shoe

interface ProductRepository {
    suspend fun listSpecialForYou() : List<Shoe>
    suspend fun listShoesByBrandId(brandId: Int) : List<Shoe>
    suspend fun getShoeById(id: Int) : Shoe
}