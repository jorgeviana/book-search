package com.example.booksearch

class Commands(val bookService: BookService, val console: Console) {
    fun accept(command: String) {
        val criteria = command.split(":")[1].trim()
        val books = bookService.search(criteria)
        books.forEachIndexed {
            index, book -> console.printLine("${index+1}: $book")
        }
    }
}