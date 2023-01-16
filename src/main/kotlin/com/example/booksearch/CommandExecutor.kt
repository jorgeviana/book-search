package com.example.booksearch

interface CommandExecutor {
    fun execute()
}

class SearchCommandExecutor(
    private val bookService: BookService,
    private val searchBooksPresenter: BooksPresenter,
    private val criteria: String,
) : CommandExecutor {
    override fun execute() {
        val books = bookService.search(criteria)
        searchBooksPresenter.present(books)
    }
}

class AddCommandExecutor(
    private val bookService: BookService,
    private val console: Console,
    private val readingList: ReadingList,
    private val index: Int,
) : CommandExecutor {
    override fun execute() {
        if (index < 1) {
            console.printLine("- add command is malformed. Number of the book should be greater or equal to 1")
            return
        }
        val lastSearchResult = bookService.lastSearchResult()
        if (index > lastSearchResult.size) {
            console.printLine("- add command is malformed. Book number not in search list")
            return
        }

        val book = lastSearchResult.get(index - 1)
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