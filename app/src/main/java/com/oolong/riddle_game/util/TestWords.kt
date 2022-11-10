package com.oolong.riddle_game.util

import com.oolong.riddle_game.util.words.*
import kotlin.random.Random

private val testWords: MutableList<String> = mutableListOf()
private val listOfLetters = listOf(
    aWords,
    bWords,
    cWords,
    dWords,
    eWords,
    fWords,
    gWords,
    hWords,
    iWords,
    jWords,
    kWords,
    lWords,
    mWords,
    nWords,
    oWords,
    pWords,
    qWords,
    rWords,
    sWords,
    tWords,
    uWords,
    vWords,
    wWords,
    xWords,
    yWords,
    zWords
)
val randomGenerator = Random(System.currentTimeMillis())

fun prepareTestWords(): MutableList<String> {
    testWords.add(aWords.random(randomGenerator))
    testWords.add(bWords.random(randomGenerator))
    testWords.add(cWords.random(randomGenerator))
    testWords.add(dWords.random(randomGenerator))
    testWords.add(eWords.random(randomGenerator))
    testWords.add(fWords.random(randomGenerator))
    testWords.add(gWords.random(randomGenerator))
    testWords.add(hWords.random(randomGenerator))
    testWords.add(iWords.random(randomGenerator))
    testWords.add(jWords.random(randomGenerator))
    testWords.add(kWords.random(randomGenerator))
    testWords.add(lWords.random(randomGenerator))
    testWords.add(mWords.random(randomGenerator))
    testWords.add(nWords.random(randomGenerator))
    testWords.add(oWords.random(randomGenerator))
    testWords.add(pWords.random(randomGenerator))
    testWords.add(qWords.random(randomGenerator))
    testWords.add(rWords.random(randomGenerator))
    testWords.add(sWords.random(randomGenerator))
    testWords.add(tWords.random(randomGenerator))
    testWords.add(uWords.random(randomGenerator))
    testWords.add(vWords.random(randomGenerator))
    testWords.add(wWords.random(randomGenerator))
    testWords.add(xWords.random(randomGenerator))
    testWords.add(yWords.random(randomGenerator))
    testWords.add(zWords.random(randomGenerator))
    return testWords
}

fun changeMissingWord(index: Int): MutableList<String>{
    testWords[index] = listOfLetters[index].random(randomGenerator)
    return testWords
}