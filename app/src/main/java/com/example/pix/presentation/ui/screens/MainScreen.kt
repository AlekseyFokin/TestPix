package com.example.pix.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.pix.presentation.navigation.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {

    Box(modifier = Modifier) {
        NavHost(
            navController = navController,
            startDestination = Routes.FlickrApiGallaryRoute
        ) {
            composable<Routes.FlickrApiGallaryRoute> {
                PicturesScreen(
                    vm = hiltViewModel<PicturesViewModel>(), putPicture =
                        {
                            navController.navigate(Routes.OnlyOnePictureRoute(it))
                        }
                )
            }

            composable<Routes.OnlyOnePictureRoute> { backStackEntry ->
                val pictureData = backStackEntry.toRoute<Routes.OnlyOnePictureRoute>()
                OnlyOnePictureScreen(pictureData.url)
            }
        }
    }
}
