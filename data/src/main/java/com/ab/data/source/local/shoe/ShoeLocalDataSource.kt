package com.ab.data.source.local.shoe

import com.ab.data.model.entity.ShoeColorsCrossRef
import com.ab.data.model.entity.ShoeEntity
import com.ab.data.model.entity.ShoeWithBrandsAndColors
import kotlinx.coroutines.flow.Flow

interface ShoeLocalDataSource  {
    suspend fun insert(shoeEntity: ShoeEntity)
    suspend fun insert(shoeEntities: List<ShoeEntity>)

    suspend fun insertShoeColorCrossRef(shoeColorsCrossRef: List<ShoeColorsCrossRef>)

    fun getByIdAsFlow(id: Int) : Flow<ShoeWithBrandsAndColors?>
    suspend fun listAll() : List<ShoeEntity>
    fun listALlByBrandId(brandId: Int) : Flow<List<ShoeWithBrandsAndColors>>

    fun listAllFavoritesFlow() : Flow<List<ShoeWithBrandsAndColors>>
    fun listRandomShoeFlow() : Flow<List<ShoeWithBrandsAndColors>>
    fun getFavoritesCountFlow() : Flow<Int>

    suspend fun delete(shoeEntity: ShoeEntity)
    suspend fun deleteAll(shoeEntities: List<ShoeEntity>)


    suspend fun markIsFavoriteById(id: Int)
    suspend fun markAsNotFavoriteById(id: Int)
}