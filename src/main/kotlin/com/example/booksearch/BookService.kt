package com.example.booksearch

class BookService {

    private val books = mutableListOf<Book>()

    fun add(book: Book) {
        books.add(book)
    }

    fun search(criteria: String): List<Book> {
        return books.filter { book ->
            book.tittle.lowercase().contains(criteria.lowercase())
                    || book.author.lowercase().contains(criteria.lowercase())
        }
    }

    fun lastSearchResult(): List<Book> {
        TODO()
    }
}
