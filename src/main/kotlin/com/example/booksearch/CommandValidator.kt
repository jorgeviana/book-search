package com.example.booksearch

interface CommandValidator {
    fun isNotValid(command: String): Boolean
}

class SearchCommandValidator(private val console: Console) : CommandValidator {
    override fun isNotValid(command: String): Boolean {
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

class AddCommandValidator(private val console: Console) : CommandValidator {
    override fun isNotValid(command: String): Boolean {
        if (!command.contains(":")) {
            console.printLine("- add command is malformed. Ex: add: 1")
            return true
        }
        if (command.split(":")[1].trim().toIntOrNull() == null) {
            console.printLine("- add command is malformed. Ex: add: 1")
            return true
        }
        return false
    }
}