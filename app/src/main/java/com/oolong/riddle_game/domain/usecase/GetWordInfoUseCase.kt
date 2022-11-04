package com.oolong.riddle_game.domain.usecase

import com.oolong.riddle_game.data.model.DictionaryApiResponse
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWordInfoUseCase @Inject constructor(
    private val dictionaryRepository: IDictionaryRepository
) {

    operator fun invoke(word: String): Flow<DictionaryApiResponse> = flow {
//        emit(
//            dictionaryRepository.getWordInfo(word)
//        )
    }
}