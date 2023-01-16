package com.example.booksearch

import com.example.booksearch.presentation.Console
import org.mockito.ArgumentMatchers.contains
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class TestConsole(private val console: Console) {

    val inOrder = Mockito.inOrder(console)

    fun verify(text: String) {
        text.trimIndent().split("\n").forEach {
                line -> inOrder.verify(console).printLine(line)
        }
    }

    fun verifyContains(text: String) {
        verify(console).printLine(contains(text.trimIndent()))
    }

    fun verifyContains(n: Int, text: String) {
        verify(console, times(n)).printLine(contains(text.trimIndent()))
    }
}