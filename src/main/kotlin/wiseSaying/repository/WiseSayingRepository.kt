package com.wiseSaying.repository

import com.wiseSaying.WiseSaying

class WiseSayingRepository {
    val wiseSayings = mutableListOf<WiseSaying>()
    var count = 0

    init {
        for (i in 1..13) {
            add("명언 $i", "작자미상 $i")
        }
    }

    fun add(content: String, author: String): Int {
        ++count
        wiseSayings.add(WiseSaying(count, content, author))
        return count
    }

    fun findAll(): List<WiseSaying> = wiseSayings

    fun findById(id: Int): WiseSaying? = wiseSayings.find { saying -> saying.id == id }

    fun remove(wiseSaying: WiseSaying) {
        wiseSayings.remove(wiseSaying)
    }

    fun update(id: Int, content: String, author: String) {
        val index = wiseSayings.indexOfFirst { saying -> saying.id == id }

        if (index != -1) {
            wiseSayings[index] = WiseSaying(id, content, author)
        }
    }
}