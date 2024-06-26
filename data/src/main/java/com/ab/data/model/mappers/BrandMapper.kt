package com.ab.data.model.mappers

import com.ab.data.model.dto.BrandDto
import com.ab.domain.model.data.Brand

fun BrandDto.toDomain() = Brand(id, name, image, stockItemCount)

fun List<BrandDto>.toBrandList() = map(BrandDto::toDomain)