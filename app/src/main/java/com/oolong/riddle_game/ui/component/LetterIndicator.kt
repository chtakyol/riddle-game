package com.oolong.riddle_game.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val letters = listOf("a", "b", "c", "d", "e", "f", "g", "h", " j", "i")

private const val ZERO_OFFSET = 164
private const val STEP = 68

@Composable
fun LetterIndicator(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    index: Int = 0
) {
    LazyRow(
        modifier = modifier
            .size(width = 336.dp, height = 64.dp)
        ,
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        userScrollEnabled = false
    ) {
        items(2) {
            LetterIndicatorItem(
                letter = "",
                state = LetterIndicatorState.EMPTY
            )
        }
        items(letters) { letter ->
            LetterIndicatorItem(
                letter = letter,
                state = if (letter == letters[index]) LetterIndicatorState.ACTIVE else LetterIndicatorState.PASSIVE
            )
        }
        items(2) {
            LetterIndicatorItem(
                letter = "",
                state = LetterIndicatorState.EMPTY
            )
        }
    }
}

@Composable
@Preview
fun PreviewLetterIndicator() {
    LetterIndicator()
}