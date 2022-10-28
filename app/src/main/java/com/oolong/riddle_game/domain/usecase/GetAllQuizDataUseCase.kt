package com.oolong.riddle_game.domain.usecase

import android.util.Log
import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.domain.model.AllQuizData
import com.oolong.riddle_game.domain.model.SingleQuizData
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import com.oolong.riddle_game.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetAllQuizDataUseCase @Inject constructor(
    private val dictionaryRepository: IDictionaryRepository
) {
    suspend operator fun invoke(words: List<String>): Flow<Resource<List<QuizDataEntity>>> {
        val a = dictionaryRepository.getWordToDB(words)
        a.collect{
            Log.d("SplashScreenUseCase", it.data.toString())
        }
        return dictionaryRepository.getWordToDB(words)
    }

}