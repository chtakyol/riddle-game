package com.oolong.riddle_game.ui.component.letter_indicator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LetterIndicator(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    letterIndicatorState: Map<String, LetterIndicatorState> = emptyMap(),
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

        items(letterIndicatorState.keys.toList()) { letter ->
            LetterIndicatorItem(
                letter = letter,
                state = letterIndicatorState[letter]!!
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
    val letterIndicatorState: Map<String, LetterIndicatorState> = mapOf(
        "a" to LetterIndicatorState.ACTIVE,
        "b" to LetterIndicatorState.PASSIVE,
        "c" to LetterIndicatorState.PASSIVE,
        "d" to LetterIndicatorState.PASSIVE,
        "e" to LetterIndicatorState.PASSIVE,
        "f" to LetterIndicatorState.PASSIVE,
        "g" to LetterIndicatorState.PASSIVE,
        "h" to LetterIndicatorState.PASSIVE,
        "j" to LetterIndicatorState.PASSIVE,
        "i" to LetterIndicatorState.PASSIVE,
    )
    LetterIndicator(
        letterIndicatorState = letterIndicatorState
    )
}