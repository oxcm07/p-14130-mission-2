package com.wiseSaying.controller

import com.wiseSaying.service.WiseSayingService

class WiseSayingController {
    val wiseSayingService = WiseSayingService()

    fun write(){
        print("명언 : ")
        val sentence = readlnOrNull() ?: ""
        print("작가 : ")
        val author = readlnOrNull() ?: ""
        val id = wiseSayingService.add(sentence, author)
        println("${id}번 명언이 등록되었습니다.")
    }

    fun list(){
        val wiseSayings = wiseSayingService.findAll()
        println("번호 / 작가 / 명언")
        println("----------------------")
        wiseSayings.asReversed().forEach { saying ->
            println("${saying.id} / ${saying.author} / ${saying.sentence}")
        }
    }

    fun delete(id: Int){
        val isDeleted = wiseSayingService.remove(id)
        if (isDeleted) {
            println("${id}번 명언이 삭제되었습니다.")
        } else {
            println("${id}번 명언은 존재하지 않습니다.")
        }
    }

    fun modify(id: Int){
        val found = wiseSayingService.findById(id)
        if(found != null) {
            println("명언(기존) : ${found.sentence}")
            print("명언 : ")
            val newSentence = readlnOrNull() ?: ""
            println("작가(기존) : ${found.author}")
            print("작가 : ")
            val newAuthor = readlnOrNull() ?: ""
            wiseSayingService.update(id, newSentence, newAuthor)
            println("${id}번 명언이 수정되었습니다.")
        } else {
            println("${id}번 명언은 존재하지 않습니다.")
        }
    }
}