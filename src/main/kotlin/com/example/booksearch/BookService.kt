package com.example.booksearch

class BookService {

    private val books = mutableListOf<Book>()

    fun add(book: Book) {
        books.add(book)
    }

    fun search(criteria: String): List<Book> {
        TODO()
    }
}
