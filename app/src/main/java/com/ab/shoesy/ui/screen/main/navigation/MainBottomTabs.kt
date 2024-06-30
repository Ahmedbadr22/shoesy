package com.ab.shoesy.ui.screen.main.navigation
import androidx.annotation.DrawableRes
import com.ab.shoesy.R
import kotlinx.serialization.Serializable

@Serializable
sealed class MainBottomTabs(
    @DrawableRes val iconRes: Int
) {
    @Serializable
    data object Home : MainBottomTabs(iconRes = R.drawable.home)

    @Serializable
    data object Discover : MainBottomTabs(iconRes = R.drawable.compass)

    @Serializable
    data object Favorite : MainBottomTabs(iconRes = R.drawable.favorite)

    @Serializable
    data object Account : MainBottomTabs(iconRes = R.drawable.user)
}