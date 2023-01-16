package com.example.booksearch.services

interface BookService {

    companion object {
        const val MAX_SEARCH_RESULT = 5L
    }

    fun search(criteria: String): List<Book>
    fun lastSearchResult(): List<Book>
}
