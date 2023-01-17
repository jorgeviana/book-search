package com.example.booksearch.command

import com.example.booksearch.presentation.BooksPresenter
import com.example.booksearch.presentation.ReadingListPresenter
import com.example.booksearch.services.BookService
import com.example.booksearch.services.ReadingList

interface CommandExecutor {
    fun execute()
}

class SearchCommandExecutor(
    private val bookService: BookService,
    private val booksPresenter: BooksPresenter,
    private val criteriaSupplier: () -> String,
) : CommandExecutor {
    override fun execute() {
        val books = bookService.search(criteriaSupplier())
        booksPresenter.present(books)
    }
}

class AddCommandExecutor(
    private val bookService: BookService,
    private val readingList: ReadingList,
    private val indexSupplier: () -> Int,
) : CommandExecutor {
    override fun execute() {
        val lastSearchResult = bookService.lastSearchResult()
        val book = lastSearchResult.get(indexSupplier() - 1)
        readingList.add(book)
    }
}

class ListCommandExecutor(
    private val readingList: ReadingList,
    private val readingListPresenter: ReadingListPresenter,
) : CommandExecutor {
    override fun execute() {
        val booksList = readingList.get()
        readingListPresenter.present(booksList)
    }
}