package com.ab.data.source.local.color

import com.ab.data.dao.ColorDao
import com.ab.data.model.entity.ColorEntity
import com.ab.data.source.local.reviewer.ReviewerLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ColorLocalDataSourceImpl(
    private val colorDao: ColorDao
) : ColorLocalDataSource {
    override suspend fun insert(colorEntities: List<ColorEntity>) {
        withContext(Dispatchers.IO) {
            colorDao.insert(colorEntities)
        }
    }

    override suspend fun list(): List<ColorEntity> = withContext(Dispatchers.IO) {
        colorDao.listAll()
    }
}