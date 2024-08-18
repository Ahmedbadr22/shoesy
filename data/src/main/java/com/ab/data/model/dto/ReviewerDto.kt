package com.ab.data.model.dto

import com.google.gson.annotations.SerializedName

data class ReviewerDto(
    val email: String,
    @SerializedName("profile_image")
    val profileImage: String?,
    @SerializedName("fullname")
    val fullName: String,
)
