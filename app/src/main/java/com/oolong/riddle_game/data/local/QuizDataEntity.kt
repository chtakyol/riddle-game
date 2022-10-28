package com.oolong.riddle_game.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuizDataEntity(
    val questionWord: String,
    val answerMeaning: String,
    @PrimaryKey val id: Int? = null
) {
}
