package com.example.booksearch.command

import com.example.booksearch.AppState
import com.example.booksearch.presentation.Console
import org.springframework.stereotype.Component

@Component
class Commands(
    private val console: Console,
    private val commandsFactory: CommandsFactory,
) {
    fun accept(command: String): AppState {
        when {
            isEmpty(command) -> {
                return AppState.CONTINUE
            }
            isExitCommand(command) -> {
                return AppState.EXIT
            }
            isList(command) -> {
                executeList()
            }
            isAdd(command) -> {
                if (isNotValidAdd(command)) {
                    return AppState.CONTINUE
                }
                executeAdd(command)
            }
            isSearch(command) -> {
                if (isNotValidSearch(command)) {
                    return AppState.CONTINUE
                }
                executeSearch(command)
            }
            else -> {
                printHelp()
            }
        }
        return AppState.CONTINUE
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

    private fun isExitCommand(command: String) = command.trim().lowercase() == "exit"

    private fun isEmpty(command: String) = command.trim().isEmpty()

    private fun isNotValidSearch(command: String) = commandsFactory.createSearchCommandValidator(command).isNotValid()

    private fun executeSearch(command: String) = commandsFactory.createSearchCommandExecutor(command).execute()

    private fun isNotValidAdd(command: String) = commandsFactory.createAddCommandValidator(command).isNotValid()

    private fun executeAdd(command: String) = commandsFactory.createAddCommandExecutor(command).execute()

    private fun executeList() = commandsFactory.createListCommandExecutor().execute()
}