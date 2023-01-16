package com.example.booksearch

import com.example.booksearch.TestData.Companion.MOBY_DICK
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions

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

    @Test
    fun `when add command does not contain a colon and a number an error is printed`() {
        commands.accept("add one")
        commands.accept("add:")
        commands.accept("add: one")

        testConsole.verifyContains(3, "- add command is malformed")
    }
    @Test
    fun `add command should contain a colon and a number`() {
        given(bookService.lastSearchResult())
            .willReturn(listOf(MOBY_DICK))

        commands.accept("add: 1")

        verify(readingList).add(MOBY_DICK)
    }

    @Test
    fun `add command should receive a number greater or equal to 1`() {
        commands.accept("add: 0")

        testConsole.verifyContains("- add command is malformed. Number of the book should be greater or equal to 1")
    }

    @Test
    fun `add command should not receive a number greater than the number of books found`() {
        given(bookService.lastSearchResult())
            .willReturn(listOf(MOBY_DICK))

        commands.accept("add: 2")

        testConsole.verifyContains("- add command is malformed. Book number not in search list")
    }

    @Test
    fun `search command must contain a colon`() {
        commands.accept(" search ")

        testConsole.verifyContains("- invalid search command")
        verifyNoInteractions(bookService)
    }

    @Test
    fun `search command must contain a criteria`() {
        commands.accept(" search : ")

        testConsole.verifyContains("- invalid search command")
        verifyNoInteractions(bookService)
    }
}