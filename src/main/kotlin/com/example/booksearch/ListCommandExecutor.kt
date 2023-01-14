package com.example.booksearch

class ListCommandExecutor(private val readingList: ReadingList, private val console: Console) {
    fun execute(command: String) {
        val booksList = readingList.get()
        if (booksList.isEmpty()) {
            console.printLine("- no books in the reading list")
        } else {
            booksList.forEach { book -> console.printLine("$book") }
        }
    }
}
