package com.example.booksearch.services

import org.springframework.stereotype.Component

@Component
class ReadingList {

    private val books = mutableSetOf<Book>()
    fun add(book: Book) {
        books.add(book)
    }

    fun get(): List<Book> {
        return books.toList()
    }
}
