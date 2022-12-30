package com.example.booksearch

import org.mockito.ArgumentMatchers.contains
import org.mockito.kotlin.verify

class TestConsole(private val console: Console) {
    fun verify(text: String) {
        text.trimIndent().split("\n").forEach {
                line -> verify(console).printLine(line)
        }
    }

    fun verifyContains(text: String) {
        verify(console).printLine(contains(text.trimIndent()))
    }
}