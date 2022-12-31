package com.example.booksearch

import com.example.booksearch.SmokeTest.Companion.restoreInputStream
import com.example.booksearch.SmokeTest.Companion.simulatesUserTyping
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GoogleBookServiceIntegrationTest {

    init {
        simulatesUserTyping("exit")
    }

    @AfterEach
    fun tearDown() {
        restoreInputStream()
    }

    @Autowired
    lateinit var googleBookService: BookService

    @Test
    fun `should get books from the real api`() {
        val result = googleBookService.search("Orlando")

        assertThat(result.size).isGreaterThan(0)
    }
}
