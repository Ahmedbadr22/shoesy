package com.ab.data.model.mappers

import com.ab.data.model.dto.TokenDto
import com.ab.domain.model.data.Token


fun TokenDto.toDomain() : Token = Token(access, refresh)