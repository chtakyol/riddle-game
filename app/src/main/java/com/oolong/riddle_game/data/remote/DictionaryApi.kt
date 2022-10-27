package com.oolong.riddle_game.data.remote

import com.oolong.riddle_game.data.model.DictionaryApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): DictionaryApiResponse

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}