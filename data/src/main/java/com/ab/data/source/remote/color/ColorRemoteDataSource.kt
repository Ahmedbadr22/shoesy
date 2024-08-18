package com.ab.data.source.remote.color

import com.ab.data.model.dto.ColorDto

interface ColorRemoteDataSource {

    suspend fun get(token: String) : List<ColorDto>
}