package com.oolong.riddle_game.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuizDataEntity::class],
    version = 1
)
abstract class QuizDataDataBase: RoomDatabase() {

    abstract val dao: QuizDataDao

    companion object {
        const val DATABASE_NAME = "quizdata_db"
    }
}