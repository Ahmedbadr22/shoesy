package com.ab.core.constants


object EndPoint {
    const val BASE_URL = "https://8c40-41-40-33-122.ngrok-free.app"
    private const val AUTH_APP = "/auth"
    private const val BRAND_APP = "/brand"
    private const val PRODUCT_APP = "/product"
    const val LOGIN_ENDPOINT = "$AUTH_APP/login"
    const val LIST_BRANDS_ENDPOINT = "$BRAND_APP/list"
    const val LIST_SPECIAL_PRODUCT_FOR_YOU_ENDPOINT = "$PRODUCT_APP/list-special-shoes-for-you"
}



