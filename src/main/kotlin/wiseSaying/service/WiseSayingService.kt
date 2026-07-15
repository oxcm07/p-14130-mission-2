package com.wiseSaying.service

import com.wiseSaying.entity.WiseSaying
import com.wiseSaying.repository.WiseSayingRepository

class WiseSayingService {
    val wiseSayingRepository = WiseSayingRepository()

    fun add(content: String, author: String) = wiseSayingRepository.add(content, author)

    fun findAll(page: Int, keywordType: String?, keyword: String?): Pair<List<WiseSaying>, Int> {
        var all = wiseSayingRepository.findAll()

        if (keywordType != null && keyword != null) {
            all = when (keywordType) {
                "content" -> all.filter { saying -> saying.content.contains(keyword) }
                "author" -> all.filter { saying -> saying.author.contains(keyword) }
                else -> all
            }
        }

        val chunks = all.asReversed().chunked(5)
        val pagedList = chunks.getOrNull(page - 1) ?: emptyList()
        val totalPages = maxOf(1, chunks.size)
        return Pair(pagedList, totalPages)
    }

    fun findById(id: Int) = wiseSayingRepository.findById(id)

    fun remove(id: Int): Boolean {
        val found = wiseSayingRepository.findById(id)
        if (found != null) {
            wiseSayingRepository.remove(found)
            return true
        }
        return false
    }

    fun update(id: Int, content: String, author: String): Boolean {
        val found = wiseSayingRepository.findById(id)
        if (found != null) {
            wiseSayingRepository.update(id, content, author)
            return true
        }
        return false
    }
}