package com.example.pix.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

import com.example.pix.presentation.navigation.AppBottomNavigation
import com.example.pix.presentation.navigation.Routes
import com.example.pix.presentation.ui.screens.PicturesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//fun MainScreen(){
fun MainScreen(navController: NavHostController) {
    Scaffold(

        bottomBar = { AppBottomNavigation(navController) }// snackBarHostState, snackBarScope) }
    ) {
//
        Box(modifier = Modifier) {
            NavHost(
                navController = navController,
                startDestination = Routes.FlickrApiGallaryRoute
            ) {
                composable<Routes.FlickrApiGallaryRoute> {
                    // PicturesScreen({navController.navigate(Routes.OnlyOnePictureRoute("url"))})
                    PicturesScreen(vm = hiltViewModel<PicturesViewModel>(),putPicture=
                        {
                            navController.navigate(Routes.OnlyOnePictureRoute(it))
                        }
                 )
                }
//                composable<Routes.OnlyOnePictureRoute> {
////                    OnlyOnePictureScreen()
////                } //{ navController.navigate(Routes.FlickrApiGallaryRoute) }
                composable<Routes.OnlyOnePictureRoute> {
                    backStackEntry ->
                    val pictureData = backStackEntry.toRoute<Routes.OnlyOnePictureRoute>()
                    OnlyOnePictureScreen(pictureData.url)
                }
            }
        }
    }
}