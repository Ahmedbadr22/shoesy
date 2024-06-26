package com.ab.domain.repository

import com.ab.domain.model.data.Brand

interface BrandRepository {

    suspend fun list() : List<Brand>
}