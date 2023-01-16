package com.example.booksearch

import com.example.booksearch.AppState.EXIT
import com.example.booksearch.command.Commands
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.InputStream
import java.util.Scanner

@SpringBootApplication
class AppRunner(private val commands: Commands, private val inputStream: InputStream) : ApplicationRunner {

	override fun run(args: ApplicationArguments?) {
		commands.printHelp()
		val scanner = Scanner(inputStream)

		do {
			val input = scanner.nextLine()
			val state = commands.accept(input)
		} while (state != EXIT)
	}
}

fun main(args: Array<String>) {
	runApplication<AppRunner>(*args)
}

enum class AppState {
	EXIT,
	CONTINUE
}

@Configuration
class InputConfig {
	@Bean
	fun inputStream(): InputStream = System.`in`
}
