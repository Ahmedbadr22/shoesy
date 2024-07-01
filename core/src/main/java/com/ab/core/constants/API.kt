package com.ab.core.constants


object API {
    // Base Url
    const val BASE_URL = "https://6e93-197-56-118-54.ngrok-free.app"

    // App
    private const val AUTH_APP = "/auth"
    private const val BRAND_APP = "/brand"
    private const val PRODUCT_APP = "/product"

    // Path
    const val ID_PATH = "id"

    // Endpoints
    const val LOGIN_ENDPOINT = "$AUTH_APP/login"
    const val LIST_BRANDS_ENDPOINT = "$BRAND_APP/list"
    const val LIST_SPECIAL_PRODUCT_FOR_YOU_ENDPOINT = "$PRODUCT_APP/list-special-shoes-for-you"
    const val LIST_SHOES_BY_BRAND_ID = "$PRODUCT_APP/list-shoes-by-brand-id/{$ID_PATH}"
    const val GET_SHOE_BY_ID = "$PRODUCT_APP/get-shoe-by-id/{$ID_PATH}"
}



