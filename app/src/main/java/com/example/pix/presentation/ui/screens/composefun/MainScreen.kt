package com.example.pix.presentation.ui.screens.composefun

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainScreen(navController: NavHostController)
{
    Scaffold(
        //snackbarHost = { SnackbarHost(snackBarHostState) },
        //bottomBar = { AppBottomNavigation(navController, snackBarHostState, snackBarScope) }
    ) {

        Box(modifier = Modifier) {
            NavHost(
                navController = navController,
                startDestination = Routes.
            ) {
                composable<Routes.RandomMarkingRoute> {
                    RandomMarkingScreen()
                }
                composable<Routes.MathMarkingRoute> {
                    MathMarkingScreen( { navController.navigate(Routes.TestNavigationScreenRoute) })
                }
                composable<Routes.ManualEditingRoute> {
                    ManualEditingScreen()
                }
                composable<Routes.PrintingRoute> {
                    PrintingScreen()
                }
                composable<Routes.GalleryRoute> {
                    GalleryScreen()
                }
                composable<Routes.TestNavigationScreenRoute> {
                    TestNavigationScreen()
                }
            }
        }
    }