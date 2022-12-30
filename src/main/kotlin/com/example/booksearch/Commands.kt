package com.example.booksearch

import org.springframework.stereotype.Component

@Component
class Commands(
    private val bookService: BookService,
    private val console: Console,
    private val readingList: ReadingList,
) {
    fun accept(command: String) {
        if (command.startsWith("list")) {
            val booksList = readingList.get()
            booksList.forEach { book -> console.printLine("$book") }
        } else if (command.startsWith("add")) {
            val index = command.split(":")[1].trim().toInt()
            val book = bookService.lastSearchResult().get(index - 1)
            readingList.add(book)
        } else {
            val criteria = command.split(":")[1].trim()
            val books = bookService.search(criteria)
            books.forEachIndexed { index, book ->
                console.printLine("${index + 1}: $book")
            }
        }
    }
}