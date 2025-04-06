package com.example.pix.presentation.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//import androidx.navigation.NavController

@Composable
fun AppBottomNavigation(
    navController: NavController,
    //snackBarHostState: SnackbarHostState,
   // snackBarScope: CoroutineScope
) {

    var selectedNavigationItem = remember { mutableStateOf(0) }

    val tabs =
        listOf(
            BottomTabs.FlickrApiGallary,
            BottomTabs.DbGallery
        )
    val navigationBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.onPrimary,
        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
        unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
        unselectedTextColor = MaterialTheme.colorScheme.onSecondary
    )

    NavigationBar(
//        containerColor = MaterialTheme.colorScheme.tertiary,
//        contentColor = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier.height(100.dp)
    ) {


        tabs.forEachIndexed { index, item ->
            NavigationBarItem(

                selected = selectedNavigationItem.value == index,
                onClick = {
                    navController.navigate(item.route)
                    selectedNavigationItem.value = index
//                    snackBarScope.launch {
//                        snackBarHostState.showSnackbar(
//                            message = "Хорошо",
//                            actionLabel = "ну и хорошо",
//                            duration = SnackbarDuration.Long
//                        )
//                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(text = stringResource(item.titleResId), fontSize = 13.sp) },
                colors = navigationBarItemColors
            )
        }
    }
}