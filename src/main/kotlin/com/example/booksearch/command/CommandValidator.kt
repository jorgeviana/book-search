package com.example.booksearch.command

import com.example.booksearch.presentation.BooksPresenter
import com.example.booksearch.services.BookService

interface CommandValidator {
    fun isNotValid(): Boolean
}

class SearchCommandValidator(
    private val command: String,
    private val presenter: BooksPresenter
    ) : CommandValidator {
    override fun isNotValid(): Boolean {
        if (!command.contains(":")) {
            presenter.presentError("- invalid search command")
            return true
        }
        val criteria = command.split(":")[1].trim()
        if (criteria.isEmpty()) {
            presenter.presentError("- invalid search command")
            return true
        }
        return false
    }
}

class AddCommandValidator(
    private val presenter: BooksPresenter,
    private val bookService: BookService,
    private val command: String,
    private val indexSupplier: () -> Int,
    ) : CommandValidator {
    override fun isNotValid(): Boolean {
        if (!command.contains(":")) {
            presenter.presentError("- add command is malformed. Ex: add: 1")
            return true
        }
        if (command.split(":")[1].trim().toIntOrNull() == null) {
            presenter.presentError("- add command is malformed. Ex: add: 1")
            return true
        }

        val index = indexSupplier()
        if (index < 1) {
            presenter.presentError("- add command is malformed. Number of the book should be greater or equal to 1")
            return true
        }
        val lastSearchResult = bookService.lastSearchResult()
        if (index > lastSearchResult.size) {
            presenter.presentError("- add command is malformed. Book number not in search list")
            return true
        }

        return false
    }
}