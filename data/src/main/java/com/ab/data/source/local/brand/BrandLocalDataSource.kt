package com.ab.data.source.local.brand

import com.ab.data.model.entity.BrandEntity
import kotlinx.coroutines.flow.Flow

interface BrandLocalDataSource  {
    suspend fun insert(brandEntity: BrandEntity)
    suspend fun insert(brandEntities: List<BrandEntity>)

    suspend fun listAll() : List<BrandEntity>

    fun listAllAsFlow() : Flow<List<BrandEntity>>

    suspend fun delete(brandEntity: BrandEntity)
    suspend fun deleteAll(brandEntities: List<BrandEntity>)
}