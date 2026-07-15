package com.wiseSaying.repository

import com.wiseSaying.WiseSaying

class WiseSayingRepository {
    val wiseSayings = mutableListOf<WiseSaying>()
    var count = 0

    fun add(sentence: String, author: String): Int {
        ++count
        wiseSayings.add(WiseSaying(count, sentence, author))
        return count
    }

    fun findAll(): List<WiseSaying> = wiseSayings

    fun findById(id: Int): WiseSaying? = wiseSayings.find { saying -> saying.id == id }

    fun remove(wiseSaying: WiseSaying) {
        wiseSayings.remove(wiseSaying)
    }

    fun update(id: Int, sentence: String, author: String) {
        val index = wiseSayings.indexOfFirst { saying -> saying.id == id }

        if (index != -1) {
            wiseSayings[index] = WiseSaying(id, sentence, author)
        }
    }
}