package com.ab.data.service

import com.ab.core.constants.API
import com.ab.data.model.dto.BrandDto
import com.ab.data.model.dto.ColorDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ColorService {
    @GET(API.LIST_COLORS_ENDPOINT)
    suspend fun list(@Header("Authorization") token: String) : Response<List<ColorDto>>
}