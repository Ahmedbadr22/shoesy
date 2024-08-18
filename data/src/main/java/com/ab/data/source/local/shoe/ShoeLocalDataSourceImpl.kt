package com.ab.data.source.local.shoe

import com.ab.data.dao.ShoeColorCrossRefDao
import com.ab.data.dao.ShoeDao
import com.ab.data.model.entity.ShoeColorsCrossRef
import com.ab.data.model.entity.ShoeEntity
import com.ab.data.model.entity.ShoeWithBrandsAndColors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ShoeLocalDataSourceImpl(
    private val productDao: ShoeDao,
    private val shoeColorCrossRefDao: ShoeColorCrossRefDao
) : ShoeLocalDataSource {
    override suspend fun insert(shoeEntity: ShoeEntity) {
        withContext(Dispatchers.IO) {
            productDao.insert(shoeEntity)
        }
    }

    override suspend fun insert(shoeEntities: List<ShoeEntity>) {
        withContext(Dispatchers.IO) {
            productDao.insert(shoeEntities)
        }
    }

    override suspend fun insertShoeColorCrossRef(shoeColorsCrossRef: List<ShoeColorsCrossRef>) {
        withContext(Dispatchers.IO) {
            shoeColorCrossRefDao.insert(shoeColorsCrossRef)
        }
    }

    override fun getByIdAsFlow(id: Int): Flow<ShoeWithBrandsAndColors?> = productDao.getByIdAsFlow(id)

    override suspend fun listAll(): List<ShoeEntity> = withContext(Dispatchers.IO) {
        productDao.listAll()
    }

    override fun listALlByBrandId(brandId: Int): Flow<List<ShoeWithBrandsAndColors>> = productDao.listByBrandID(brandId)
    override fun listAllFavoritesFlow(): Flow<List<ShoeWithBrandsAndColors>> = productDao.listAllFavoritesFlow()
    override fun listRandomShoeFlow(): Flow<List<ShoeWithBrandsAndColors>> = productDao.listRandomShoeFlow()

    override fun getFavoritesCountFlow(): Flow<Int> = productDao.getFavoritesCountFlow()


    override suspend fun delete(shoeEntity: ShoeEntity) {
        withContext(Dispatchers.IO) {
            productDao.delete(shoeEntity)
        }
    }

    override suspend fun deleteAll(shoeEntities: List<ShoeEntity>) {
        withContext(Dispatchers.IO) {
            productDao.delete(shoeEntities)
        }
    }

    override suspend fun markIsFavoriteById(id: Int) {
        withContext(Dispatchers.IO) {
            productDao.markIsFavoriteById(id)
        }
    }

    override suspend fun markAsNotFavoriteById(id: Int) {
        withContext(Dispatchers.IO) {
            productDao.markAsNotFavoriteById(id)
        }
    }

}