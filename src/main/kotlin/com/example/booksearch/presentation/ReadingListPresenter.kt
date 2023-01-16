package com.example.booksearch.presentation

import com.example.booksearch.services.Book

class ReadingListPresenter(private val console: Console) {

    fun present(books: List<Book>) {
        if (books.isEmpty()) {
            console.printLine("- no books in the reading list")
            return
        }
        console.printLine("The reading list is:")
        defaultPresentation(console, books)
    }
}