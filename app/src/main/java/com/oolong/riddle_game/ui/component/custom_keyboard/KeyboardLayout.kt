package com.oolong.riddle_game.ui.component.custom_keyboard

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardLayout(
    modifier: Modifier = Modifier,
    onRemoveClicked: () -> Unit = {},
    onPassClicked: () -> Unit = {},
    onEnterClicked: () -> Unit = {},
    onClick: (String) -> Unit = {}
) {
    val row1Letters = arrayOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p")
    val row2Letters = arrayOf("a", "s", "d", "f", "g", "h", "j", "k", "l")
    val row3Letters = arrayOf("z", "x", "c", "v", "b", "n", "m")

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            for (letter in row1Letters) {
                KeyboardButton(
                    letter = letter,
                    onClick = onClick
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            for (letter in row2Letters) {
                KeyboardButton(
                    letter = letter,
                    onClick = onClick
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            for (letter in row3Letters) {
                KeyboardButton(
                    letter = letter,
                    onClick = onClick
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            FunctionalButton(
                letter = "Remove",
                onClick = onRemoveClicked
            )
            FunctionalButton(
                letter = "Pass",
                onClick = onPassClicked
            )
            FunctionalButton(
                letter = "OK",
                onClick = onEnterClicked
            )
        }
    }
}

@Composable
@Preview
fun PreviewKeyboardLayout() {
    KeyboardLayout()
}