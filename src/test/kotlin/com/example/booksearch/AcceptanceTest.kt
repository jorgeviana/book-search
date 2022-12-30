package com.example.booksearch

import com.example.booksearch.TestData.Companion.CHARLES_WAVES_BOOK
import com.example.booksearch.TestData.Companion.MOBY_DICK
import com.example.booksearch.TestData.Companion.THE_WAVES
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class AcceptanceTest {

    val bookService = BookService()
    val console = mock<Console>()
    val commands = Commands(bookService, console)
    val testConsole = TestConsole(console)

    @Test
    fun `should add book to reading list`() {
        // given the repository has books
        bookService.add(THE_WAVES)
        bookService.add(CHARLES_WAVES_BOOK)
        bookService.add(MOBY_DICK)

        // when I search for 'wave'
        commands.accept("search: wave")

        // then it's printed on the console the matching books
        testConsole.verify("""
            1: ${THE_WAVES}
            2: ${CHARLES_WAVES_BOOK}
        """)

        // and when I choose to add the 1st book to the reading list
        commands.accept("add: 1")

        // then I can see the content of the reading list
        commands.accept("list")
        testConsole.verify("""
            ${THE_WAVES}
        """)
    }
}