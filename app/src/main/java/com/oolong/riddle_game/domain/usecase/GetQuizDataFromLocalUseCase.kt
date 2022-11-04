package com.oolong.riddle_game.domain.usecase

import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.domain.model.SingleQuizData
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import com.oolong.riddle_game.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuizDataFromLocalUseCase @Inject constructor(
    private val dictionaryRepository: IDictionaryRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<QuizDataEntity>>> {
        return dictionaryRepository.getQuizData()
    }
}
