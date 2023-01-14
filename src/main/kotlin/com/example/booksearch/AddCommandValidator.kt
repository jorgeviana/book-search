package com.example.booksearch

class AddCommandValidator(private val console: Console) {
    fun isNotValid(command: String): Boolean {
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