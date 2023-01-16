package com.example.booksearch.presentation

import com.example.booksearch.services.Book

class SearchBooksPresenter(private val console: Console) : BooksPresenter(console) {

    override fun present(books: List<Book>) {
        defaultPresentation(books)
    }
}