package com.wiseSaying.controller

import com.wiseSaying.service.WiseSayingService

class WiseSayingController {
    val wiseSayingService = WiseSayingService()

    fun write() {
        print("명언 : ")
        val content = readlnOrNull() ?: ""
        print("작가 : ")
        val author = readlnOrNull() ?: ""
        val id = wiseSayingService.add(content, author)
        println("${id}번 명언이 등록되었습니다.")
    }

    fun list(page: Int = 1, keywordType: String? = null, keyword: String? = null) {
        val (pagedSayings, totalPages) = wiseSayingService.findAll(page, keywordType, keyword)

        if (keywordType != null && keyword != null) {
            println("----------------------")
            println("검색타입 : $keywordType")
            println("검색어 : $keyword")
            println("----------------------")
        }

        println("번호 / 작가 / 명언")
        println("----------------------")
        pagedSayings.forEach { saying ->
            println("${saying.id} / ${saying.author} / ${saying.content}")
        }
        println("----------------------")

        val pageButtons = (1..totalPages).map { i ->
            if (i == page) "[$i]" else "$i"
        }
        println("페이지 : ${pageButtons.joinToString(" / ")}")
    }

    fun delete(id: Int) {
        val isDeleted = wiseSayingService.remove(id)
        if (isDeleted) {
            println("${id}번 명언이 삭제되었습니다.")
        } else {
            println("${id}번 명언은 존재하지 않습니다.")
        }
    }

    fun modify(id: Int) {
        val found = wiseSayingService.findById(id)
        if (found != null) {
            println("명언(기존) : ${found.content}")
            print("명언 : ")
            val newContent = readlnOrNull() ?: ""
            println("작가(기존) : ${found.author}")
            print("작가 : ")
            val newAuthor = readlnOrNull() ?: ""
            wiseSayingService.update(id, newContent, newAuthor)
            println("${id}번 명언이 수정되었습니다.")
        } else {
            println("${id}번 명언은 존재하지 않습니다.")
        }
    }
}