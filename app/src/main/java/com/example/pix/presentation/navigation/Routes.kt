package com.example.pix.presentation.navigation

import com.example.pix.domain.entity.Picture
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object FlickrApiGallaryRoute : Routes()

    @Serializable
    data class OnlyOnePictureRoute(val url: String): Routes()//(val pic: Picture) : Routes()

    @Serializable
    data object DataBaseGallaryRoute : Routes()
}