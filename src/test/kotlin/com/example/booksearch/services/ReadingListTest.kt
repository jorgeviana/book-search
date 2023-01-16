package com.example.booksearch.services

import com.example.booksearch.TestData.Companion.CHARLES_WAVES_BOOK
import com.example.booksearch.TestData.Companion.THE_WAVES
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReadingListTest {

    val readingList = ReadingList()

    @Test
    fun `reading list starts empty`() {
        assertThat(readingList.get()).isEmpty()
    }

    @Test
    fun `should add books to the reading list`() {
        readingList.add(THE_WAVES)
        readingList.add(CHARLES_WAVES_BOOK)

        assertThat(readingList.get()).containsExactly(
            THE_WAVES,
            CHARLES_WAVES_BOOK,
        )
    }

    @Test
    fun `should not add duplicate books to the reading list`() {
        readingList.add(THE_WAVES)
        readingList.add(THE_WAVES)

        assertThat(readingList.get()).containsExactly(
            THE_WAVES,
        )
    }
}