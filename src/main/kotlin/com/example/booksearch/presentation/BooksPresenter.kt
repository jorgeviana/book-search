package com.example.booksearch.presentation

import com.example.booksearch.services.Book

abstract class BooksPresenter(private val console: Console) {
    abstract fun present(books: List<Book>)

    fun defaultPresentation(books: List<Book>) {
        books.forEachIndexed { index, book ->
            if (index > 0) {
                console.printLine("")
            }
            console.printLine("Book #${index + 1}")
            console.printLine("Tittle: ${book.tittle}")
            console.printLine("Author: ${book.author}")
            console.printLine("Publisher: ${book.publishingCompany}")
        }
    }

    fun presentError(message: String) {
        console.printLine(message)
    }
}
