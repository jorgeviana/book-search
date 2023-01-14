package com.example.booksearch

class SearchCommandExecutor(private val bookService: BookService, private val console: Console) {
    fun execute(command: String) {
        val criteria = command.split(":")[1].trim()
        val books = bookService.search(criteria)
        books.forEachIndexed { index, book ->
            console.printLine("${index + 1}: $book")
        }
    }

}
