package com.oolong.riddle_game.ui.component.custom_keyboard

import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardButton(
    modifier: Modifier = Modifier,
    letter: String = "q",
    backgroundColor: Color = Color.Gray,
    contentColor: Color = Color.Black,
    onClick: (String) -> Unit = {}
) {
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
    TextButton(
        modifier = modifier
            .size(width = 32.dp, height = 64.dp),
        colors = buttonColors,
        onClick = {
            onClick(letter)
        }
    ) {
        Text(
            text = letter.uppercase()
        )
    }
}

@Composable
fun FunctionalButton(
    modifier: Modifier = Modifier,
    letter: String = "q",
    backgroundColor: Color = Color.Gray,
    contentColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
    TextButton(
        modifier = modifier,
        colors = buttonColors,
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = letter.uppercase()
        )
    }
}

@Composable
@Preview
fun PreviewKeyboardButton() {
    KeyboardButton()
}