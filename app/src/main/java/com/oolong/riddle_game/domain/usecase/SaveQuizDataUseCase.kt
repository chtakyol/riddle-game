package com.oolong.riddle_game.domain.usecase

import com.oolong.riddle_game.data.local.QuizDataEntity
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import javax.inject.Inject

class SaveQuizDataUseCase @Inject constructor(
    private val dictionaryRepository: IDictionaryRepository
) {
    suspend operator fun invoke(quizDataEntities: List<QuizDataEntity>) {
        dictionaryRepository.saveQuizDataToLocal(quizDataEntities)
    }
}