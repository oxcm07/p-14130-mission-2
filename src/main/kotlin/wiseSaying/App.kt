package com.wiseSaying

import com.wiseSaying.controller.WiseSayingController

class App {
    private val controller = WiseSayingController()

    fun run() {
        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")
            val input = readlnOrNull() ?: ""

            if (input == "등록") {
                controller.write()
            }
            else if (input == "목록") {
                controller.list()
            }
            else if (input.startsWith("목록?")) {
                val queryString = input.substringAfter("목록?")

                val params = queryString.split("&").associate {
                    val bits = it.split("=", limit = 2)
                    val key = bits.getOrNull(0) ?: ""
                    val value = bits.getOrNull(1) ?: ""
                    key to value
                }

                val keywordType = params["keywordType"]
                val keyword = params["keyword"]

                controller.list(keywordType, keyword)
            }
            else if (input.startsWith("삭제?id=")) {
                val id = input.substringAfter("삭제?id=").toIntOrNull()
                id?.let { controller.delete(it) }
            }
            else if (input.startsWith("수정?id=")) {
                val id = input.substringAfter("수정?id=").toIntOrNull()
                id?.let { controller.modify(it) }
            }
            else if (input == "종료") {
                println("프로그램을 종료합니다.")
                break
            }
            else {
                println("존재하지 않는 명령입니다.")
            }
        }
    }
}