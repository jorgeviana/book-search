package com.example.booksearch

import org.springframework.stereotype.Component

@Component
class BookService {

    private val books = mutableListOf<Book>()
    private var lastSearchResult = mutableListOf<Book>()

    fun add(book: Book) {
        books.add(book)
    }

    fun search(criteria: String): List<Book> {
        val searchResult = books.filter { book ->
            book.tittle.lowercase().contains(criteria.lowercase())
                    || book.author.lowercase().contains(criteria.lowercase())
        }
        updateLastSearchResult(searchResult)
        return searchResult
    }

    fun lastSearchResult(): List<Book> {
        return lastSearchResult.toList()
    }

    private fun updateLastSearchResult(searchResult: List<Book>) {
        lastSearchResult = mutableListOf()
        lastSearchResult.addAll(searchResult)
    }
}
