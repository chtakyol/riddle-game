package com.oolong.riddle_game.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizData(quizData: QuizDataEntity)

    @Query("SELECT * FROM quizdataentity")
    fun getQuizData(): List<QuizDataEntity>

    @Query("DELETE FROM quizdataentity")
    fun clearQuizData()
}