package com.example.pix.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.compose.PixTheme
import com.example.pix.R
import com.example.pix.presentation.ui.screens.MainScreen
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
                MainScreen(navController)
            }
        }
    }
}