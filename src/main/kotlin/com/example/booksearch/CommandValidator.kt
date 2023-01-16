package com.example.booksearch

interface CommandValidator {
    fun isNotValid(command: String): Boolean
}

class SearchCommandValidator(private val console: Console) : CommandValidator {
    override fun isNotValid(command: String): Boolean {
        // TODO are we mixing presentation?
        if (!command.contains(":")) {
            console.printLine("- invalid search command")
            return true
        }
        val criteria = command.split(":")[1].trim()
        if (criteria.isEmpty()) {
            console.printLine("- invalid search command")
            return true
        }
        return false
    }
}

class AddCommandValidator(
    private val console: Console,
    private val bookService: BookService,
    private val indexSupplier: () -> Int,
    ) : CommandValidator {
    override fun isNotValid(command: String): Boolean {
        // TODO are we mixing presentation?
        if (!command.contains(":")) {
            console.printLine("- add command is malformed. Ex: add: 1")
            return true
        }
        if (command.split(":")[1].trim().toIntOrNull() == null) {
            console.printLine("- add command is malformed. Ex: add: 1")
            return true
        }

        val index = indexSupplier()
        if (index < 1) {
            console.printLine("- add command is malformed. Number of the book should be greater or equal to 1")
            return true
        }
        val lastSearchResult = bookService.lastSearchResult()
        if (index > lastSearchResult.size) {
            console.printLine("- add command is malformed. Book number not in search list")
            return true
        }

        return false
    }
}