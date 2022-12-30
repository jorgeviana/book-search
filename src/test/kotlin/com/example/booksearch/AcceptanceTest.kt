package com.example.booksearch

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AcceptanceTest {

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

    val MOBY_DICK = Book(
        author = "Herman Melville",
        tittle = "Moby Dick",
        publishingCompany = "Top Books",
    )

    val bookService = BookService()
    val console = mock<Console>()
    val commands = Commands(bookService, console)

    @Test
    fun `should add book to reading list`() {
        // given the repository has books
        bookService.add(THE_WAVES)
        bookService.add(CHARLES_WAVES_BOOK)
        bookService.add(MOBY_DICK)

        // when I search for 'wave'
        commands.accept("search: wave")

        // then it's printed on the console the matching books
        verifyConsole("""
            1: ${THE_WAVES}
            2: ${CHARLES_WAVES_BOOK}
        """)

        // and when I choose to add the 1st book to the reading list
        commands.accept("add: 1")

        // then I can see the content of the reading list
        commands.accept("list")
        verifyConsole("""
            ${THE_WAVES}
        """)
    }

    fun verifyConsole(text: String) {
        text.trimIndent().split("\n").forEach {
            line -> verify(console).printLine(line)
        }
    }
}