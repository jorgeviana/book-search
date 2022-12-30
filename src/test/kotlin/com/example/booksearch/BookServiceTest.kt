package com.example.booksearch

import com.example.booksearch.TestData.Companion.CHARLES_WAVES_BOOK
import com.example.booksearch.TestData.Companion.MOBY_DICK
import com.example.booksearch.TestData.Companion.THE_WAVES
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BookServiceTest {

    val bookService = BookService()

    @Test
    fun `search should work on tittle and author`() {
        bookService.add(THE_WAVES)
        bookService.add(CHARLES_WAVES_BOOK)
        bookService.add(MOBY_DICK)

        val searchResult = bookService.search("wave")

        assertThat(searchResult).containsExactly(
            THE_WAVES, CHARLES_WAVES_BOOK
        )
    }

    @Test
    fun `get last search result`() {
        // given the following books
        bookService.add(THE_WAVES)
        bookService.add(CHARLES_WAVES_BOOK)
        bookService.add(MOBY_DICK)

        // when I search for wave
        bookService.search("wave")

        // then the last search result should contain all books with wave
        val searchResult = bookService.lastSearchResult()

        assertThat(searchResult).containsExactly(
            THE_WAVES, CHARLES_WAVES_BOOK
        )
    }

    @Test
    fun `get last search result when performing more than one search`() {
        // given the following books
        bookService.add(THE_WAVES)
        bookService.add(CHARLES_WAVES_BOOK)
        bookService.add(MOBY_DICK)

        // when I search for wave and then search for moby
        bookService.search("wave")
        bookService.search("moby")

        // then the last search result should contain all books with moby
        val searchResult = bookService.lastSearchResult()

        assertThat(searchResult).containsExactly(
            MOBY_DICK
        )
    }
}