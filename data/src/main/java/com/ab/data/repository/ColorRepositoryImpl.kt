package com.ab.data.repository

import com.ab.data.model.mappers.toEntities
import com.ab.data.source.local.color.ColorLocalDataSource
import com.ab.data.source.local.reviewer.ReviewerLocalDataSource
import com.ab.data.source.remote.color.ColorRemoteDataSource
import com.ab.domain.repository.ColorRepository


class ColorRepositoryImpl(
    private val colorLocalDataSource: ColorLocalDataSource,
    private val colorRemoteDataSource: ColorRemoteDataSource
): ColorRepository {
    override suspend fun listFromRemoteAndInsertToLocal(token: String) {
        val colorDtoList = colorRemoteDataSource.get(token)
        val colorEntities = colorDtoList.toEntities()
        colorLocalDataSource.insert(colorEntities)
    }
}