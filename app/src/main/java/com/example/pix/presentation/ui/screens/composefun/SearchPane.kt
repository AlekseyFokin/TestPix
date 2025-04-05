package com.example.pix.presentation.ui.screens.composefun

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pix.R


@Composable
fun SearchPane(
    onClickButton: (String) -> Unit={},

    isLoading: Boolean=false
) {
    val string= remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        TextField(
            value =string.value,
            onValueChange = { //onChangeSearchString(it)
                            string.value=it},
//            label = {
//                Text(
//                    stringResource(R.string.search_field_label),
//                    color = Color.Blue,
//                    fontSize = 8.sp
//                )
//            },
//            maxLines = 1,
//            singleLine = true,
//            modifier = Modifier
//                .padding(PaddingValues(0.dp))
//                .height(50.dp)
//                .width(250.dp),
            maxLines = 1,

            placeholder = { Text(text = stringResource(R.string.search_field_label), fontSize = 10.sp) },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .width(250.dp)
          //      .background(color = MaterialTheme.colorScheme.background, shape = RectangleShape)

        )

        Button(
            onClick = {
                onClickButton(string.value)
            },
            modifier = Modifier
                .size(55.dp),
            enabled = !(isLoading),
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "content description",
      //          tint = Color.Black,
               modifier = Modifier.scale(6.0f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSearchPane(){
    SearchPane()
}