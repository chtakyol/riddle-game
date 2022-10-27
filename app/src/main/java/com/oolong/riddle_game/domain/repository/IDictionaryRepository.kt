package com.oolong.riddle_game.domain.repository

import com.oolong.riddle_game.data.model.DictionaryApiResponse

interface IDictionaryRepository {

    suspend fun getWordInfo(word: String): DictionaryApiResponse
}