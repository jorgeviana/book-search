package com.example.booksearch

import org.springframework.stereotype.Component

@Component
class CommandsFactory(
    private val bookService: BookService,
    private val readingList: ReadingList,
    private val console: Console,
) {
    fun indexSupplier(command: String) = { command.split(":")[1].trim().toInt() }
    fun criteriaSupplier(command: String) = { command.split(":")[1].trim() }

    fun createAddCommandValidator(command: String) =
        AddCommandValidator(AddPresenter(console), bookService, command, indexSupplier(command))

    fun createAddCommandExecutor(command: String) =
        AddCommandExecutor(bookService, readingList, indexSupplier(command))

    fun createListCommandExecutor() = ListCommandExecutor(readingList, ReadingListPresenter(console))

    fun createSearchCommandValidator(command: String) =
        SearchCommandValidator(command, SearchBooksPresenter(console))

    fun createSearchCommandExecutor(command: String) =
        SearchCommandExecutor(bookService, SearchBooksPresenter(console), criteriaSupplier(command))
}