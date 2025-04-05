package com.example.pix.presentation.ui.screens.composefun

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pix.R

@Composable
fun AlertDialogScreen(
    text: String = stringResource(R.string.alert_dialog_screen_message),
    textType: String = stringResource(R.string.alert_dialog_screen_label),
    resetError: () -> Unit = {},
    dismissError: () -> Unit = {}
) {
    AlertDialog(
        modifier = Modifier.border(2.dp, Color.Red),
        shape = RectangleShape,
        onDismissRequest = {
            resetError()
            //resetErrorMessage()
        },
        title = {
            Text(
                text = textType,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                )
            )
        },
        text = {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                )
            )
        },
        confirmButton = {
            Button({
                resetError()
            }, contentPadding = PaddingValues(10.dp)) {
                Text(
                    stringResource(R.string.alert_dialog_screen_label_button_reload),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                    )
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    dismissError()
                },
                contentPadding = PaddingValues(10.dp)
            ) {
                Text(
                    stringResource(R.string.alert_dialog_screen_label_button_dismiss),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                    ),
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewAlertDialogScreen() {
    AlertDialogScreen()
}