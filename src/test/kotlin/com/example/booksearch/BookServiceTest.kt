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
}