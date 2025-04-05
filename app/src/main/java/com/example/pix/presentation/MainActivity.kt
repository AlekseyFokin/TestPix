package com.example.pix.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import com.example.compose.PixTheme
import com.example.pix.R
import com.example.pix.presentation.ui.screens.PicturesScreen
import com.example.pix.presentation.ui.screens.composefun.MainScreen

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)


        enableEdgeToEdge()
        setContent {
            PixTheme {
                val navController = rememberNavController()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                MainScreen(navController)
                         //    PicturesScreen()
            }
        }

    }
}