package com.oolong.riddle_game.data.repository

import android.util.Log
import com.oolong.riddle_game.data.model.DictionaryApiResponse
import com.oolong.riddle_game.data.remote.DictionaryApi
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi
): IDictionaryRepository {

    override suspend fun getWordInfo(word: String): DictionaryApiResponse {
        Log.d("Dummy", word)
        return dictionaryApi.getWordInfo(word)
    }
}