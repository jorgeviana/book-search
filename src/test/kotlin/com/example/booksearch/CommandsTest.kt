package com.example.booksearch

import com.example.booksearch.TestData.Companion.CHARLES_WAVES_BOOK
import com.example.booksearch.TestData.Companion.THE_WAVES
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class CommandsTest {

    val bookService = mock<BookService>()
    val console = mock<Console>()
    val testConsole = TestConsole(console)
    val readingList = mock<ReadingList>()
    val commandsFactory = CommandsFactory(bookService, readingList, console)
    val commands = Commands(console, commandsFactory)

    @Test
    fun `should accept the exit command`() {
        val result: AppState = commands.accept("exit")

        assertThat(result).isEqualTo(AppState.EXIT)
    }
    @Test
    fun `should not process empty command`() {
        val result: AppState = commands.accept(" ")

        assertThat(result).isEqualTo(AppState.CONTINUE)
    }

    @Test
    fun `should accept the search command`() {
        given(bookService.search("wave"))
            .willReturn(listOf(THE_WAVES, CHARLES_WAVES_BOOK))

        commands.accept("search: wave")

        testConsole.verify("""
            Book #1
            Tittle: ${THE_WAVES.tittle}
            Author: ${THE_WAVES.author}
            Publisher: ${THE_WAVES.publishingCompany}
            
            Book #2
            Tittle: ${CHARLES_WAVES_BOOK.tittle}
            Author: ${CHARLES_WAVES_BOOK.author}
            Publisher: ${CHARLES_WAVES_BOOK.publishingCompany}
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
            The reading list is:
            Book #1
            Tittle: ${THE_WAVES.tittle}
            Author: ${THE_WAVES.author}
            Publisher: ${THE_WAVES.publishingCompany}
        """)
    }
}