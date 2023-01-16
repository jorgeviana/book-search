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

class SearchBooksPresenter(private val console: Console) : BooksPresenter(console) {

    override fun present(books: List<Book>) {
        defaultPresentation(books)
    }
}

class ReadingListPresenter(private val console: Console) : BooksPresenter(console) {

    override fun present(books: List<Book>) {
        if (books.isEmpty()) {
            console.printLine("- no books in the reading list")
            return
        }
        console.printLine("The reading list is:")
        defaultPresentation(books)
    }
}

class AddPresenter(console: Console) : BooksPresenter(console) {
    override fun present(books: List<Book>) {
    }
}