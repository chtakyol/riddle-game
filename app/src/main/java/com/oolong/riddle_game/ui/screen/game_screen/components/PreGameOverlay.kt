package com.oolong.riddle_game.ui.screen.game_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreGameOverlay(
    onPlayClick: () -> Unit = {}
) {
    val background = Color(0xFFF5F5F5)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GameTitle(modifier = Modifier)

            StartButton {
                onPlayClick()
            }

            HowToPlayButton {

            }
        }
    }
}

@Composable
fun GameTitle(
    modifier: Modifier
) {
    val backgroundColor = Color(0xFF7DA388)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Riddle Rally",
                style = TextStyle(
                    color = Color.White
                ),
                fontSize = 32.sp
            )
            Text(
                text = "Daily Quiz Game",
                style = TextStyle(
                    color = Color.White
                ),
                fontSize = 13.sp
            )
        }

    }
}

@Composable
fun StartButton(
    onClick: () -> Unit
) {
    val buttonBackgroundColor = Color.White
    val borderColor = Color.Black
    val textColor = Color.Black
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = 267.dp, height = 102.dp)
            .background(buttonBackgroundColor)
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = "Start",
            style = TextStyle(
                color = textColor
            ),
            fontSize = 32.sp
        )
    }
}

@Composable
fun HowToPlayButton(
    onClick: () -> Unit
) {
    val buttonBackgroundColor = Color(0xFFF2F2F2)
    val borderColor = Color(0xFFC5C5C5)
    val textColor = Color(0xFF696969)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(buttonBackgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .size(width = 203.dp, height = 74.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = "How to play",
            style = TextStyle(
                color = textColor
            ),
            fontSize = 32.sp
        )
    }
}

@Composable
@Preview
fun PreviewPreGameOverlay() {
    PreGameOverlay()
}