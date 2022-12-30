package com.example.booksearch

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.ByteArrayInputStream

@SpringBootTest
class SmokeTest {

    init {
        simulatesUserTyping("exit")
    }

    @AfterEach
    fun tearDown() {
        restoreInputStream()
    }

    @Autowired
    lateinit var appRunner: AppRunner

    @Test
    @Timeout(2)
    fun `application loads correctly`() {
        assertThat(appRunner).isNotNull
    }

    companion object {
        val copyIn = System.`in`
        fun restoreInputStream() {
            System.setIn(copyIn)
        }
        fun simulatesUserTyping(command: String) {
            System.setIn(ByteArrayInputStream(command.toByteArray()))
        }
    }
}