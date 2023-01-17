package com.example.booksearch.command

import com.example.booksearch.presentation.BooksPresenter
import com.example.booksearch.presentation.Console
import com.example.booksearch.presentation.ReadingListPresenter
import com.example.booksearch.services.BookService
import com.example.booksearch.services.ReadingList
import org.springframework.stereotype.Component

@Component
class CommandsFactory(
    private val bookService: BookService,
    private val readingList: ReadingList,
    private val console: Console,
) {
    fun secondArgument(command: String) = command.split(":")[1].trim()
    fun indexSupplier(command: String) = { secondArgument(command).toInt() }
    fun nullableIndexSupplier(command: String) = { secondArgument(command).toIntOrNull() }
    fun criteriaSupplier(command: String) = { secondArgument(command) }

    fun createAddCommandValidator(command: String) =
        AddCommandValidator(BooksPresenter(console), bookService, command, nullableIndexSupplier(command))

    fun createAddCommandExecutor(command: String) =
        AddCommandExecutor(bookService, readingList, indexSupplier(command))

    fun createListCommandExecutor() = ListCommandExecutor(readingList, ReadingListPresenter(console))

    fun createSearchCommandValidator(command: String) =
        SearchCommandValidator(command, BooksPresenter(console), criteriaSupplier(command))

    fun createSearchCommandExecutor(command: String) =
        SearchCommandExecutor(bookService, BooksPresenter(console), criteriaSupplier(command))
}