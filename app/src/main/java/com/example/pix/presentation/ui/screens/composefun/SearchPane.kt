package com.example.pix.presentation.ui.screens.composefun

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pix.R

@Composable
fun SearchPane(
    onClickButton: (String) -> Unit = {},

    isLoading: Boolean = false
) {
    val string = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TextField(
            value = string.value,
            onValueChange = { string.value = it },
            maxLines = 1,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_field_label),
                    fontSize = 10.sp
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                //  .background(color = MaterialTheme.colorScheme.background, shape = CircleShape)
                .fillMaxWidth()
                .border(1.dp, Color.Black, CircleShape),
            shape = CircleShape,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { string.value = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Clear Icon"
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )

        Button(
            onClick = {
                onClickButton(string.value)
            },
            modifier = Modifier
                .width(255.dp)
                .padding(top = 12.dp),
            shape = RoundedCornerShape(10.dp),
            enabled = !(isLoading),
        ) {
            Text(text = "Поиск")
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "content description",
                modifier = Modifier.scale(1.0f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSearchPane() {
    SearchPane()
}