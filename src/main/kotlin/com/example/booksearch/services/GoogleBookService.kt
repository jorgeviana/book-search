package com.example.booksearch.services

import org.springframework.stereotype.Component

@Component
class GoogleBookService(private val apiService: ApiService) : BookService {

    private val resultMapper = ResultMapper()
    private var lastSearchResult = mutableListOf<Book>()

    override fun search(criteria: String): List<Book> {
        val params = mapOf(
            "q" to criteria,
            "maxResults" to BookService.MAX_SEARCH_RESULT,
        )

        val result = apiService.getMethod(
            "https://www.googleapis.com/books/v1/volumes?q={q}&maxResults={maxResults}",
            params,
            GoogleResult::class.java
        )

        if (result.isEmpty) {
            updateLastSearchResult(listOf())
            return listOf()
        }

        val books = resultMapper.map(result.get().books())
        updateLastSearchResult(books)
        return books
    }

    override fun lastSearchResult(): List<Book> {
        return lastSearchResult.toList()
    }

    private fun updateLastSearchResult(searchResult: List<Book>) {
        lastSearchResult = mutableListOf()
        lastSearchResult.addAll(searchResult)
    }

    class ResultMapper {
        fun map(googleBooks: List<GoogleResult.GoogleBook>): List<Book> {
            return googleBooks.map(::map)
        }
        fun map(googleBook: GoogleResult.GoogleBook): Book {
            return Book(
                tittle = googleBook.title ?: "<EMPTY>",
                author = if (googleBook.authors == null) "<EMPTY>" else googleBook.authors.joinToString(),
                publishingCompany = googleBook.publisher ?: "<EMPTY>"
            )
        }
    }
    class GoogleResult(val items: List<VolumeInfo>) {
        fun books(): List<GoogleBook> {
            return items.map(VolumeInfo::volumeInfo)
        }

        class VolumeInfo(val volumeInfo: GoogleBook)
        data class GoogleBook(
            val title: String?,
            val authors: List<String>?,
            val publisher: String?
        )
    }
}