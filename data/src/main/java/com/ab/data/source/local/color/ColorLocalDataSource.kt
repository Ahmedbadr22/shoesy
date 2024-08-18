package com.ab.data.source.local.color

import com.ab.data.model.entity.ColorEntity

interface ColorLocalDataSource {

    suspend fun insert(colorEntities: List<ColorEntity>)
    suspend fun list() : List<ColorEntity>
}