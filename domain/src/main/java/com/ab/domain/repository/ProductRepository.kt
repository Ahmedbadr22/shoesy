package com.ab.domain.repository

import com.ab.domain.model.data.Shoe

interface ProductRepository {

    suspend fun listSpecialForYou() : List<Shoe>
}