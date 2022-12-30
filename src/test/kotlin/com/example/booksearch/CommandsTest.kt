package com.example.booksearch

import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CommandsTest {

    val THE_WAVES = Book(
        author = "Virginia Woolf",
        tittle = "The Waves",
        publishingCompany = "Best Books",
    )

    val CHARLES_WAVES_BOOK = Book(
        author = "Charles Waves",
        tittle = "The Untold Story",
        publishingCompany = "Other Books",
    )

    val bookService = mock<BookService>()
    val console = mock<Console>()
    val commands = Commands(bookService, console)

    @Test
    fun `should accept the search command`() {
        given(bookService.search("wave"))
            .willReturn(listOf(THE_WAVES, CHARLES_WAVES_BOOK))

        commands.accept("search: wave")

        verifyConsole("""
            1: ${THE_WAVES}
            2: ${CHARLES_WAVES_BOOK}
        """)
    }

    fun verifyConsole(text: String) {
        text.trimIndent().split("\n").forEach {
                line -> verify(console).printLine(line)
        }
    }
}