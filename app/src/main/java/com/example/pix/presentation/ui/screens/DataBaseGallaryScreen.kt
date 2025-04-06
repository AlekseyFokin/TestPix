package com.example.pix.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pix.R
import com.example.pix.presentation.ui.screens.composefun.SearchPane

@Composable
fun DataBaseGallaryScreen(vm: DataBaseGalleryScreenViewModel = viewModel()) {

    Box(contentAlignment = Alignment.TopStart) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.Top
        )
        {
            //SearchPane(clickStartRequestButton, isLoading)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
                verticalArrangement = Arrangement.SpaceAround,
                contentPadding = PaddingValues(top = 18.dp, bottom = 18.dp)
            ) {
//                items(
//                    count = picturesData.itemCount,
//                )
//                { index ->
//                    if (picturesData[index] != null) {
//                        ItemPicturesScreen(
//                            picturesData[index]!!, minSize
//                        )
//                    }
//                }
            }
        }

        Button( //кнопка очистки БД
            onClick = {
              //  dismissError()
            },
            contentPadding = PaddingValues(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)

        ) {
            Text(
                stringResource(R.string.db_gallery_button_clear_db),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                ),
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}