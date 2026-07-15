package com

import com.wiseSaying.controller.WiseSayingController

class App {
    private val controller = WiseSayingController()

    fun run() {
        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")
            val input = readlnOrNull() ?: ""

            if (input == "종료") {
                println("프로그램을 종료합니다.")
                break
            }

            when {
                input == "등록" -> controller.write()
                input == "목록" -> controller.list()

                input.startsWith("목록?") -> {
                    val queryString = input.substringAfter("목록?")
                    val params = queryString.split("&").associate {
                        val bits = it.split("=", limit = 2)
                        val key = bits.getOrNull(0) ?: ""
                        val value = bits.getOrNull(1) ?: ""
                        key to value
                    }
                    val page = params["page"]?.toIntOrNull() ?: 1
                    val keywordType = params["keywordType"]
                    val keyword = params["keyword"]
                    controller.list(page, keywordType, keyword)
                }

                input.startsWith("삭제?") -> {
                    val id = input.substringAfter("id=").toIntOrNull()
                    if (id != null) controller.delete(id) else println("올바른 id를 입력해주세요.")
                }

                input.startsWith("수정?") -> {
                    val id = input.substringAfter("id=").toIntOrNull()
                    if (id != null) controller.modify(id) else println("올바른 id를 입력해주세요.")
                }

                else -> println("존재하지 않는 명령어입니다.")
            }
        }
    }
}