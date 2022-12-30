package com.example.booksearch

import com.example.booksearch.TestData.Companion.CHARLES_WAVES_BOOK
import com.example.booksearch.TestData.Companion.THE_WAVES
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CommandsTest {

    val bookService = mock<BookService>()
    val console = mock<Console>()
    val testConsole = TestConsole(console)
    val readingList = mock<ReadingList>()
    val commands = Commands(bookService, console, readingList)

    @Test
    fun `unknown commands should print help`() {
        commands.accept("unknown random command")

        testConsole.verifyContains("""
            The available commands are: search, add, list, exit
        """)
    }

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

    @Test
    fun `should accept the add command for the 1st book`() {
        given(bookService.lastSearchResult())
            .willReturn(listOf(THE_WAVES, CHARLES_WAVES_BOOK))

        commands.accept("add: 1")

        verify(readingList).add(THE_WAVES)
    }

    @Test
    fun `should accept the add command for the 2nd book`() {
        given(bookService.lastSearchResult())
            .willReturn(listOf(THE_WAVES, CHARLES_WAVES_BOOK))

        commands.accept("add: 2")

        verify(readingList).add(CHARLES_WAVES_BOOK)
    }

    @Test
    fun `should print the reading list`() {
        given(readingList.get())
            .willReturn(listOf(THE_WAVES))

        commands.accept("list")

        testConsole.verify("""
            ${THE_WAVES}
        """)
    }
}