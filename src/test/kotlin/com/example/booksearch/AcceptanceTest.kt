package com.example.booksearch

import com.example.booksearch.TestData.Companion.CHARLES_WAVES_BOOK
import com.example.booksearch.TestData.Companion.MOBY_DICK
import com.example.booksearch.TestData.Companion.THE_WAVES
import com.example.booksearch.command.Commands
import com.example.booksearch.command.CommandsFactory
import com.example.booksearch.presentation.Console
import com.example.booksearch.services.FakeBookService
import com.example.booksearch.services.ReadingList
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class AcceptanceTest {

    val bookService = FakeBookService()
    val console = mock<Console>()
    val testConsole = TestConsole(console)
    val readingList = ReadingList()
    val commandsFactory = CommandsFactory(bookService, readingList, console)
    val commands = Commands(console, commandsFactory)

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
            Book #1
            Tittle: ${THE_WAVES.tittle}
            Author: ${THE_WAVES.author}
            Publisher: ${THE_WAVES.publishingCompany}
            
            Book #2
            Tittle: ${CHARLES_WAVES_BOOK.tittle}
            Author: ${CHARLES_WAVES_BOOK.author}
            Publisher: ${CHARLES_WAVES_BOOK.publishingCompany}
        """)

        // and when I choose to add the 1st book to the reading list
        commands.accept("add: 1")

        // then I can see the content of the reading list
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