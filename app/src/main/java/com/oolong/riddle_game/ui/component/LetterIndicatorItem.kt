package com.oolong.riddle_game.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class LetterIndicatorState {
    ACTIVE, PASSIVE, CORRECT, WRONG, PASSED, EMPTY
}

@Composable
fun LetterIndicatorItem(
    letter: String = "a",
    state: LetterIndicatorState = LetterIndicatorState.ACTIVE
) {

    val backgroundColor = when (state) {
        LetterIndicatorState.ACTIVE -> {
            Color.LightGray
        }
        LetterIndicatorState.PASSIVE -> {
            Color.DarkGray
        }
        LetterIndicatorState.CORRECT -> {
            Color.LightGray
        }
        LetterIndicatorState.WRONG -> {
            Color.LightGray
        }
        LetterIndicatorState.PASSED -> {
            Color.LightGray
        }
        LetterIndicatorState.EMPTY -> {
            Color.White
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(64.dp))
            .size(width = 64.dp, height = 64.dp)
            .background(backgroundColor)
    ) {
        Text(
            text = letter.uppercase(),
        )
    }

}

@Composable
@Preview
fun PreviewLetterIndicatorItem() {
    LetterIndicatorItem()
}