package com.oolong.riddle_game.ui.component.letter_indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
        LetterIndicatorState.ACTIVE -> { Color.White }
        LetterIndicatorState.PASSIVE -> { Color(0xFFF2F2F2) }
        LetterIndicatorState.CORRECT -> { Color(0xFF7DA388) }
        LetterIndicatorState.WRONG -> { Color(0xFFDF585F) }
        LetterIndicatorState.PASSED -> { Color(0xFFD3AF38) }
        LetterIndicatorState.EMPTY -> { MaterialTheme.colors.background }
    }

    val textColor = when (state) {
        LetterIndicatorState.ACTIVE -> { Color.Black }
        LetterIndicatorState.PASSIVE -> { Color.Gray }
        LetterIndicatorState.CORRECT -> { Color.White }
        LetterIndicatorState.WRONG -> { Color.White }
        LetterIndicatorState.PASSED -> { Color.White }
        LetterIndicatorState.EMPTY -> { Color.LightGray }
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
            style = TextStyle(
                color = textColor
            )
        )
    }

}

@Composable
@Preview
fun PreviewLetterIndicatorItemActive() {
    LetterIndicatorItem( state = LetterIndicatorState.ACTIVE)
}

@Composable
@Preview
fun PreviewLetterIndicatorItemPassive() {
    LetterIndicatorItem( state = LetterIndicatorState.PASSIVE )
}

@Composable
@Preview
fun PreviewLetterIndicatorItemCorrect() {
    LetterIndicatorItem( state = LetterIndicatorState.CORRECT )
}

@Composable
@Preview
fun PreviewLetterIndicatorItemWrong() {
    LetterIndicatorItem( state = LetterIndicatorState.WRONG)
}

@Composable
@Preview
fun PreviewLetterIndicatorItemPassed() {
    LetterIndicatorItem( state = LetterIndicatorState.PASSED )
}

@Composable
@Preview
fun PreviewLetterIndicatorItemEmpty() {
    LetterIndicatorItem( state = LetterIndicatorState.EMPTY )
}