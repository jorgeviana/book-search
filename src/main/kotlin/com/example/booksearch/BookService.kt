package com.example.booksearch

import org.springframework.stereotype.Component

private const val MAX_SEARCH_RESULT = 5L

interface GoogleBookService {
    fun search(criteria: String): List<Book>
    fun lastSearchResult(): List<Book>
}

@Component
class BookService : GoogleBookService {

    private val books = mutableListOf<Book>()
    private var lastSearchResult = mutableListOf<Book>()

    fun add(book: Book) {
        books.add(book)
    }

    override fun search(criteria: String): List<Book> {
        val searchResult = books.filter { book ->
            book.tittle.lowercase().contains(criteria.lowercase())
                    || book.author.lowercase().contains(criteria.lowercase())
        }
            .stream()
            .limit(MAX_SEARCH_RESULT)
            .toList()

        updateLastSearchResult(searchResult)
        return searchResult
    }

    override fun lastSearchResult(): List<Book> {
        return lastSearchResult.toList()
    }

    private fun updateLastSearchResult(searchResult: List<Book>) {
        lastSearchResult = mutableListOf()
        lastSearchResult.addAll(searchResult)
    }
}
