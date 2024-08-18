package com.ab.data.repository

import com.ab.data.dao.ReviewDao
import com.ab.data.dao.ReviewerDao
import com.ab.data.model.entity.ShoeWithBrandsAndColors
import com.ab.data.model.mappers.getReviewerEntities
import com.ab.data.model.mappers.getReviewsEntities
import com.ab.data.model.mappers.toDomain
import com.ab.data.model.mappers.toDomainList
import com.ab.data.model.mappers.toEntities
import com.ab.data.model.mappers.toShoeColorCrossRefList
import com.ab.data.model.request.AddFavoriteRequest
import com.ab.data.source.local.shoe.ShoeLocalDataSource
import com.ab.data.source.remote.product.ProductRemoteDataSource
import com.ab.domain.model.data.Shoe
import com.ab.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val shoeLocalDataSource: ShoeLocalDataSource,
    private val reviewDao: ReviewDao,
    private val reviewerDao: ReviewerDao
) : ProductRepository {

    override suspend fun listAllShoeFromRemoteToLocal(token: String) {
        val shoeDtoList = productRemoteDataSource.listAll(token)

        val shoeColorCrossRefEntities = shoeDtoList.toShoeColorCrossRefList()
        val reviewEntities = shoeDtoList.getReviewsEntities()
        val reviewerEntities = shoeDtoList.getReviewerEntities()
        val shoeEntities = shoeDtoList.toEntities()

        shoeLocalDataSource.insertShoeColorCrossRef(shoeColorCrossRefEntities)
        reviewDao.insert(reviewEntities)
        reviewerDao.insert(reviewerEntities)
        shoeLocalDataSource.insert(shoeEntities)
    }

    override fun listShoesByBrandId(brandId: Int): Flow<List<Shoe>> = shoeLocalDataSource
        .listALlByBrandId(brandId)
        .map { shoeWithBrandAndColors -> shoeWithBrandAndColors.toDomainList() }

    override fun getByIdAsFlow(id: Int): Flow<Shoe?> = shoeLocalDataSource
        .getByIdAsFlow(id)
        .map { shoeWithBrandAndColors: ShoeWithBrandsAndColors? ->  shoeWithBrandAndColors?.toDomain()}

    override fun listAllFavoritesFlow(): Flow<List<Shoe>> = shoeLocalDataSource
        .listAllFavoritesFlow()
        .map { shoesWithBrandAndColors -> shoesWithBrandAndColors.toDomainList() }

    override fun listRandomShoeFlow(): Flow<List<Shoe>> = shoeLocalDataSource
        .listRandomShoeFlow()
        .map { shoesWithBrandAndColors -> shoesWithBrandAndColors.toDomainList() }

    override fun getFavoritesCountFlow(): Flow<Int> = shoeLocalDataSource.getFavoritesCountFlow()

    override suspend fun markIsFavoriteBYId(token: String, id: Int) {
        val addFavoriteRequest = AddFavoriteRequest(id)
        productRemoteDataSource.markAsFavorite(token, addFavoriteRequest)
        shoeLocalDataSource.markIsFavoriteById(id)
    }

    override suspend fun markAsNotFavoriteById(token: String, id: Int) {
        productRemoteDataSource.markAsNotFavorite(token, id)
        shoeLocalDataSource.markAsNotFavoriteById(id)
    }

}