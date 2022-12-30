package com.example.booksearch

import org.springframework.stereotype.Component

@Component
class Console {
    fun printLine(line: String) {
        println(line)
    }
}