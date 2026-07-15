package com.wiseSaying.service

import com.wiseSaying.repository.WiseSayingRepository

class WiseSayingService {
    val wiseSayingRepository = WiseSayingRepository()

    fun add(sentence: String, author: String) = wiseSayingRepository.add(sentence, author)

    fun findAll() = wiseSayingRepository.findAll()

    fun findById(id: Int) = wiseSayingRepository.findById(id)

    fun remove(id: Int): Boolean {
        val found = wiseSayingRepository.findById(id)
        if (found != null) {
            wiseSayingRepository.remove(found)
            return true
        }
        return false
    }

    fun update(id: Int, sentence: String, author: String): Boolean {
        val found = wiseSayingRepository.findById(id)
        if (found != null) {
            wiseSayingRepository.update(id, sentence, author)
            return true
        }
        return false
    }
}