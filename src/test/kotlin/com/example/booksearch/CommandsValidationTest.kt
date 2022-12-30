package com.example.booksearch

import org.junit.jupiter.api.Test
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
}