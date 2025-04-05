package com.example.pix.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object FlickrApiGallaryRoute : Routes()

    @Serializable
    data class OnlyOnePictureRoute(val url: String) : Routes()

    @Serializable
    data object DataBaseGallaryRoute : Routes()
}