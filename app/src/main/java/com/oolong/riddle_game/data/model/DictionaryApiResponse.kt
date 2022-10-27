package com.oolong.riddle_game.data.model

import com.oolong.riddle_game.domain.model.SingleQuizData

class DictionaryApiResponse : ArrayList<DictionaryApiResponseItem>() {

    fun toQuestionData(): SingleQuizData {
        return SingleQuizData(
            questionWord = this[0].word,
            answerMeaning = this[0].meanings[0].definitions[0].definition
        )
    }
}