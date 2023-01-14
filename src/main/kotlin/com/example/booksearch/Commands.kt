package com.example.booksearch

import org.springframework.stereotype.Component

@Component
class Commands(
    private val bookService: BookService,
    private val console: Console,
    private val readingList: ReadingList,
) {
    /**
     * TODO this class needs refactoring badly :)
     */
    fun accept(command: String) {
        when {
            isList(command) -> {
                val executor = ListCommandExecutor(readingList, console)
                executor.execute(command)
            }
            isAdd(command) -> {
                val validator = AddCommandValidator(console)
                if (validator.isNotValid(command)) {
                    return
                }
                val executor = AddCommandExecutor(bookService, console, readingList)
                executor.execute(command)
            }
            isSearch(command) -> {
                val validator = SearchCommandValidator(console)
                if (validator.isNotValid(command)) {
                    return
                }

                val executor = SearchCommandExecutor(bookService, console)
                executor.execute(command)
            }
            else -> {
                printHelp()
            }
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