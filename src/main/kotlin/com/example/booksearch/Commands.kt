package com.example.booksearch

import org.springframework.stereotype.Component

@Component
class Commands(
    private val bookService: BookService,
    private val console: Console,
    private val readingList: ReadingList,
) {
    fun accept(command: String) {
        if (isList(command)) {
            val booksList = readingList.get()
            booksList.forEach { book -> console.printLine("$book") }

        } else if (isAdd(command)) {
            val index = command.split(":")[1].trim().toInt()
            val book = bookService.lastSearchResult().get(index - 1)
            readingList.add(book)
        } else if (isSearch(command)){
            val criteria = command.split(":")[1].trim()
            val books = bookService.search(criteria)
            books.forEachIndexed { index, book ->
                console.printLine("${index + 1}: $book")
            }
        } else {
            printHelp()
        }
    }

    fun printHelp() {
        console.printLine("""
            
            The available commands are: search, add, list, exit
            
            Examples:
            
            search: moby dick       // searches for books
            add: 1                  // adds book #1 from last search to the reading list
            list                    // lists all books from reading list
            exit                    // exits the application
            
            
        """.trimIndent())
    }

    private fun isList(command: String) = command.trim().lowercase().equals("list")

    private fun isAdd(command: String) = command.trimStart().lowercase().startsWith("add")

    private fun isSearch(command: String) = command.trimStart().lowercase().startsWith("search")
}