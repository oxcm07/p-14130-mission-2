package com.wiseSaying.service

import com.wiseSaying.WiseSaying
import com.wiseSaying.repository.WiseSayingRepository

class WiseSayingService {
    val wiseSayingRepository = WiseSayingRepository()

    fun add(content: String, author: String) = wiseSayingRepository.add(content, author)

    fun findAll(keywordType: String? = null, keyword: String? = null): List<WiseSaying> {
        val all = wiseSayingRepository.findAll()

        if(keywordType != null && keyword != null) {
            return when (keywordType) {
                "content" -> all.filter { saying -> saying.content.contains(keyword) }
                "author" -> all.filter { saying -> saying.author.contains(keyword) }
                else -> all
            }
        }
        return all
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