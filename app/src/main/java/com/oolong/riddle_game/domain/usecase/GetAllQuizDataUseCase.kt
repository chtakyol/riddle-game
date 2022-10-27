package com.oolong.riddle_game.domain.usecase

import com.oolong.riddle_game.domain.model.AllQuizData
import com.oolong.riddle_game.domain.model.SingleQuizData
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllQuizDataUseCase @Inject constructor(
    private val dictionaryRepository: IDictionaryRepository
) {

    suspend operator fun invoke(words: List<String>): Flow<AllQuizData> {
        val allQuizData = mutableListOf<SingleQuizData>()
        for (word in words) {
            allQuizData.add(
                dictionaryRepository.getWordInfo(word).toQuestionData()
            )
        }
        return flow {
            emit(
                AllQuizData(
                    allQuizData = allQuizData
                )
            )
        }
    }
}