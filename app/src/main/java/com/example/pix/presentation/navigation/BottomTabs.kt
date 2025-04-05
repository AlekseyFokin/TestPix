package com.example.pix.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pix.R
import com.example.pix.presentation.navigation.Routes.DataBaseGallaryRoute
import com.example.pix.presentation.navigation.Routes.FlickrApiGallaryRoute
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomTabs<T>(
    val route: T,
    val titleResId: Int,
    @Contextual val icon: ImageVector
) {
    @Serializable
    data object FlickrApiGallary : BottomTabs<FlickrApiGallaryRoute>(
        route = FlickrApiGallaryRoute,
        titleResId = R.string.bottom_tab_name_api_gallary,
        icon = Icons.Filled.Star
    )

    @Serializable
    data object DbGallery : BottomTabs<DataBaseGallaryRoute>(
        route = DataBaseGallaryRoute,
        titleResId = R.string.bottom_tab_name_db_gallary,
        icon = Icons.Filled.Favorite
    )
}