package com.example.booksearch.services

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.util.*

@Component
class ApiService(restTemplateBuilder: RestTemplateBuilder) {

    private val restTemplate = restTemplateBuilder.build()

    fun <T> getMethod(url: String, params: Map<String, *>, type: Class<T>): Optional<T> {
        try {
            val result: ResponseEntity<T> = restTemplate.getForEntity(
                url,
                type,
                params
            )
            val body = result.body
            if (body == null) {
                return Optional.empty<T>()
            }
            return Optional.of(body)
        } catch (e: RuntimeException) {
            return Optional.empty()
        }
    }
}