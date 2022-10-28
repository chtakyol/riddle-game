package com.oolong.riddle_game.data.repository

import android.util.Log
import com.oolong.riddle_game.data.local.QuizDataDao
import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.data.model.DictionaryApiResponse
import com.oolong.riddle_game.data.remote.DictionaryApi
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import com.oolong.riddle_game.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val dictionaryApi: DictionaryApi,
    private val dao: QuizDataDao
    // TODO inject app utility data
): IDictionaryRepository {

    override suspend fun getWordInfo(word: String): DictionaryApiResponse {
        Log.d("Dummy", word)
        return dictionaryApi.getWordInfo(word)
    }

    override suspend fun getWordToDB(words: List<String>): Flow<Resource<List<QuizDataEntity>>> {
        dao.clearQuizData()
        Log.d("SplashScreen", "Removed old data")
        var resource: Resource<List<QuizDataEntity>>
        try {
            for (word in words) {
                val questionData = dictionaryApi.getWordInfo(word).toQuestionData()
                dao.insertQuizData(
                    QuizDataEntity(
                        questionWord = questionData.questionWord,
                        answerMeaning = questionData.answerMeaning
                    )
                )
            }
            Log.d("SplashScreen", "New data pulled")
        } catch (e: HttpException) {
            resource = Resource.Error(errorMessage = "Error: $e")
        } catch (e: IOException) {
            resource = Resource.Error(errorMessage = "Error: $e")

        }
        val quizData = dao.getQuizData()
        resource = Resource.Success(data = quizData)
        Log.d("SplashScreen", resource.data.toString())

        return flow {
            emit(resource)
        }
    }
}