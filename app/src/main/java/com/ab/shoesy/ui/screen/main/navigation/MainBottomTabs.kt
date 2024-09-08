package com.ab.shoesy.ui.screen.main.navigation
import androidx.annotation.DrawableRes
import com.ab.shoesy.R
import kotlinx.serialization.Serializable

@Serializable
sealed class MainBottomTabs(
    @DrawableRes val iconRes: Int,
    val route: String,
) {
    @Serializable
    data object Home : MainBottomTabs(iconRes = R.drawable.home, route = "home")

    @Serializable
    data object Cart : MainBottomTabs(iconRes = R.drawable.bag, route = "cart")

    @Serializable
    data object Favorite : MainBottomTabs(iconRes = R.drawable.favorite, route = "favorite")

    @Serializable
    data object Profile : MainBottomTabs(iconRes = R.drawable.user, route = "profile")
}