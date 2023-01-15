package com.example.booksearch

interface CommandExecutor {
    fun execute(command: String)
}

class SearchCommandExecutor(
    private val bookService: BookService,
    private val console: Console,
) : CommandExecutor {
    override fun execute(command: String) {
        val criteria = command.split(":")[1].trim()
        val books = bookService.search(criteria)
        books.forEachIndexed { index, book ->
            console.printLine("${index + 1}: $book")
        }
    }
}

class AddCommandExecutor(
    private val bookService: BookService,
    private val console: Console,
    private val readingList: ReadingList,
) : CommandExecutor {
    override fun execute(command: String) {
        val index = command.split(":")[1].trim().toInt()
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
    private val console: Console,
) : CommandExecutor {
    override fun execute(command: String) {
        val booksList = readingList.get()
        if (booksList.isEmpty()) {
            console.printLine("- no books in the reading list")
        } else {
            booksList.forEach { book -> console.printLine("$book") }
        }
    }
}