package com.example.booksearch

interface CommandExecutor {
    fun execute()
}

class SearchCommandExecutor(
    private val bookService: BookService,
    private val searchBooksPresenter: BooksPresenter,
    private val criteriaSupplier: () -> String,
) : CommandExecutor {
    override fun execute() {
        val books = bookService.search(criteriaSupplier())
        searchBooksPresenter.present(books)
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
    private val readingListPresenter: BooksPresenter,
) : CommandExecutor {
    override fun execute() {
        val booksList = readingList.get()
        readingListPresenter.present(booksList)
    }
}