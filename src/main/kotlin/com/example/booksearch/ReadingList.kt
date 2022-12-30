package com.example.booksearch

class ReadingList {

    private val books = mutableListOf<Book>()
    fun add(book: Book) {
        books.add(book)
    }

    fun get(): List<Book> {
        return books.toList()
    }
}
