package com.example.booksearch

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.Scanner

@SpringBootApplication
class AppRunner(private val commands: Commands) : ApplicationRunner {

	override fun run(args: ApplicationArguments?) {
		val scanner = Scanner(System.`in`)
		var input = scanner.next()
		while (input.trim().lowercase() != "exit") {
			commands.accept(input)
			input = scanner.next()
		}
	}
}

fun main(args: Array<String>) {
	runApplication<AppRunner>(*args)
}
