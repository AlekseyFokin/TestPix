package com.example.pix.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTargetMarker
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pix.R
import com.example.pix.domain.entity.Picture

@Composable
fun OnlyOnePictureScreen(picture:Picture=Picture(url ="https://i.pinimg.com/736x/80/30/3b/80303b969d5a9132eaa939ea16d8002d.jpg" ,title="example")){
Column(modifier = Modifier
    .fillMaxSize()
    .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 24.dp),
    verticalArrangement = Arrangement.SpaceBetween)
{

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
            .padding(3.dp).graphicsLayer(),

        error = painterResource(R.drawable.ic_error),
    )

    Button(
        onClick = {
           // onClickButton(string.value)
        },
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .width(255.dp),
        shape = RoundedCornerShape(10.dp),
        //enabled = !(isLoading),
    ) {
        Text(text = "Добавить в мою галарею")
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "",
            modifier = Modifier.scale(1.0f)
        )
    }
}
}

@Preview
@Composable
fun PreviewOnlyOnePictureScreen(){
    OnlyOnePictureScreen()
}
