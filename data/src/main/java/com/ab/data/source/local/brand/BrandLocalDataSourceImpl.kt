package com.ab.data.source.local.brand

import com.ab.data.dao.BrandDao
import com.ab.data.model.entity.BrandEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


class BrandLocalDataSourceImpl(
    private val brandDao: BrandDao
): BrandLocalDataSource {
    override suspend fun insert(brandEntity: BrandEntity) {
        withContext(Dispatchers.IO) {
            brandDao.insert(brandEntity)
        }
    }

    override suspend fun insert(brandEntities: List<BrandEntity>) {
        withContext(Dispatchers.IO) {
            brandDao.insert(brandEntities)
        }
    }

    override suspend fun listAll(): List<BrandEntity> = withContext(Dispatchers.IO) {
        brandDao.listAll()
    }

    override fun listAllAsFlow(): Flow<List<BrandEntity>> = brandDao
        .listAllAsFlow()
        .flowOn(Dispatchers.IO)

    override suspend fun delete(brandEntity: BrandEntity) {
        withContext(Dispatchers.IO) {
            brandDao.delete(brandEntity)
        }
    }

    override suspend fun deleteAll(brandEntities: List<BrandEntity>) {
        withContext(Dispatchers.IO) {
            brandDao.delete(brandEntities)
        }
    }
}