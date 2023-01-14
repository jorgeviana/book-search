package com.example.booksearch

class AddCommandExecutor(
    private val bookService: BookService,
    private val console: Console,
    private val readingList: ReadingList,
) {
    fun execute(command: String) {
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