package com.example.booksearch

import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class CommandsValidationTest {

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
    fun `should inform user when the reading list is empty`() {
        given(readingList.get()).willReturn(listOf())

        commands.accept("list")

        testConsole.verify("""
           - no books in the reading list
        """)
    }
}