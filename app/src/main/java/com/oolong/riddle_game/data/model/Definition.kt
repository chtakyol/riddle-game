package com.oolong.riddle_game.data.model

data class Definition(
    val antonyms: List<Any>,
    val definition: String,
    val example: String,
    val synonyms: List<String>
)