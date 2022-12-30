package com.example.booksearch

class Commands(val bookService: BookService, val console: Console, val readingList: ReadingList) {
    fun accept(command: String) {
        if (command.startsWith("add")) {
            val index = command.split(":")[1].trim().toInt()
            val book = bookService.lastSearchResult().get(index - 1)
            readingList.add(book)
        }
        else {
            val criteria = command.split(":")[1].trim()
            val books = bookService.search(criteria)
            books.forEachIndexed {
                    index, book -> console.printLine("${index+1}: $book")
            }
        }
    }
}