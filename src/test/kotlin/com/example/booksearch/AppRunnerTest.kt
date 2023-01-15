package com.example.booksearch

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.io.InputStream

class AppRunnerTest {

    val commands = mock<Commands>()

    @BeforeEach
    fun setUp() {
        given(commands.accept(any())).willReturn(AppState.CONTINUE)
        given(commands.accept("exit")).willReturn(AppState.EXIT)
    }

    @Test
    fun `first command is exit`() {
        val userInput = userInput(
            "exit"
        )

        runApp(userInput)

        verifyAcceptedExactly(commands,
            "exit"
        )
    }

    @Test
    fun `accepts list command then exits`() {
        val userInput = userInput(
            "list",
            "exit"
        )

        runApp(userInput)

        verifyAcceptedExactly(commands,
            "list",
            "exit"
        )
    }

    private fun userInput(vararg input: String): InputStream {
        return input.joinToString(separator = System.lineSeparator())
            .byteInputStream()
    }

    private fun runApp(userInput: InputStream) {
        val appRunner = AppRunner(commands, userInput)
        val noArguments = null
        appRunner.run(noArguments)
    }

    private fun verifyAcceptedExactly(commandsParser: Commands, vararg commands: String) {
        verify(commandsParser).printHelp()
        commands.forEach { c -> verify(commandsParser).accept(c) }
        verifyNoMoreInteractions(commandsParser)
    }
}