package com.example.booksearch.presentation

import com.example.booksearch.services.Book

class BooksPresenter(private val console: Console) {

    fun present(books: List<Book>) {
        defaultPresentation(console, books)
    }

    fun presentError(message: String) {
        console.printLine(message)
    }
}

fun defaultPresentation(console: Console, books: List<Book>) {
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
