package com.oolong.riddle_game.domain.repository

import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.data.model.DictionaryApiResponse
import com.oolong.riddle_game.domain.model.SingleQuizData
import com.oolong.riddle_game.util.Resource
import kotlinx.coroutines.flow.Flow

interface IDictionaryRepository {

    suspend fun getWordInfoFromRemote(word: String): SingleQuizData

    suspend fun saveQuizDataToLocal(quizDataEntities: List<QuizDataEntity>)

    suspend fun getQuizData(): Flow<Resource<List<QuizDataEntity>>>
}