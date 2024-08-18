package com.ab.data.source.remote.color

import com.ab.core.utils.safeApiCall
import com.ab.data.model.dto.ColorDto
import com.ab.data.service.ColorService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ColorRemoteDataSourceImpl(
    private val colorService: ColorService
) : ColorRemoteDataSource {
    override suspend fun get(token: String): List<ColorDto>  = safeApiCall {
        withContext(Dispatchers.IO) {
            colorService.list(token)
        }
    }

}