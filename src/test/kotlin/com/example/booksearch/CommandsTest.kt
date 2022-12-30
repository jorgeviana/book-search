package com.example.booksearch

import com.example.booksearch.TestData.Companion.CHARLES_WAVES_BOOK
import com.example.booksearch.TestData.Companion.THE_WAVES
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class CommandsTest {

    val bookService = mock<BookService>()
    val console = mock<Console>()
    val commands = Commands(bookService, console)
    val testConsole = TestConsole(console)

    @Test
    fun `should accept the search command`() {
        given(bookService.search("wave"))
            .willReturn(listOf(THE_WAVES, CHARLES_WAVES_BOOK))

        commands.accept("search: wave")

        testConsole.verify("""
            1: ${THE_WAVES}
            2: ${CHARLES_WAVES_BOOK}
        """)
    }

}