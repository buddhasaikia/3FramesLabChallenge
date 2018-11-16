package com.buddha.a3frameslabchallengeapp.utils

import com.buddha.a3frameslabchallengeapp.data.Word
import java.util.*

class WordComparator : Comparator<Word> {

    override fun compare(word1: Word, word2: Word): Int {
        val key1 = word1.count
        val key2 = word2.count
        return key2 - key1
    }
}
