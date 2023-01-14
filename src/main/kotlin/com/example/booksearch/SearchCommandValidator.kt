package com.example.booksearch

class SearchCommandValidator(private val console: Console) {
    fun isNotValid(command: String): Boolean {
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