package com.oolong.riddle_game.domain.repository

import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.data.model.DictionaryApiResponse
import com.oolong.riddle_game.util.Resource
import kotlinx.coroutines.flow.Flow

interface IDictionaryRepository {

    suspend fun getWordInfo(word: String): DictionaryApiResponse

    suspend fun getWordToDB(words: List<String>): Flow<Resource<List<QuizDataEntity>>>
}