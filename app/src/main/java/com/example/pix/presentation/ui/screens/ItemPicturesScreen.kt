package com.example.pix.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pix.R
import com.example.pix.domain.entity.Picture

@Composable
fun ItemPicturesScreen(picture: Picture, minSize: Dp, putPicture:(String)->Unit) {// элемент ленивого списка фото

    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.size(minSize).padding(6.dp),
        onClick ={putPicture(picture.url)}
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(picture.url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_placeholder),
            contentDescription = picture.title,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .clip(shape = RectangleShape)
                .padding(3.dp),
            error = painterResource(R.drawable.ic_error),
        )
    }
}
