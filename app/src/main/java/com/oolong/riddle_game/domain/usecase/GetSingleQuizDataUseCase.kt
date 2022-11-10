package com.oolong.riddle_game.domain.usecase

import com.oolong.riddle_game.domain.model.SingleQuizData
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import com.oolong.riddle_game.util.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetSingleQuizDataUseCase @Inject constructor(
    private val dictionaryRepository: IDictionaryRepository
) {
    operator fun invoke (word: String): Flow<Resource<SingleQuizData>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(data = dictionaryRepository.getWordInfoFromRemote(word)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}