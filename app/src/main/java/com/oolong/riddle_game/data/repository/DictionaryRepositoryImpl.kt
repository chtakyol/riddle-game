package com.oolong.riddle_game.data.repository

import android.util.Log
import com.oolong.riddle_game.data.local.QuizDataDao
import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.data.model.DictionaryApiResponse
import com.oolong.riddle_game.data.remote.DictionaryApi
import com.oolong.riddle_game.domain.model.SingleQuizData
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import com.oolong.riddle_game.util.Resource
import com.oolong.riddle_game.util.changeMissingWord
import com.oolong.riddle_game.util.prepareTestWords
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

    override suspend fun getWordInfoFromRemote(word: String): SingleQuizData {
        return dictionaryApi.getWordInfo(word).toQuestionData()
    }

    override suspend fun saveQuizDataToLocal(quizDataEntities: List<QuizDataEntity>) {
        dao.clearQuizData()
        for (quizDataEntity in quizDataEntities) {
            dao.insertQuizData(quizDataEntity)
        }
    }

    override suspend fun getQuizData(): Flow<Resource<List<QuizDataEntity>>> {
        val quizData = dao.getQuizData()
        val resource = Resource.Success(data = quizData)
        return flow {
            emit(resource)
        }
    }
}