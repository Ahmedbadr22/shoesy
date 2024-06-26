package com.ab.core.constants


object EndPoint {
    const val BASE_URL = "https://dac4-102-44-239-173.ngrok-free.app"
    private const val AUTH_APP = "/auth"
    private const val BRAND_APP = "/brand"
    const val LOGIN_ENDPOINT = "$AUTH_APP/login"
    const val LIST_BRANDS_ENDPOINT = "$BRAND_APP/list"
}



