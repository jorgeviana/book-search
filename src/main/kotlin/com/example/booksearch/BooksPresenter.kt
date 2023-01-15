package com.example.booksearch

import org.springframework.stereotype.Component

@Component
class BooksPresenter(private val console: Console) {

    fun present(books: List<Book>) {
        books.forEachIndexed { index, book ->
            if (index > 0) {
                console.printLine("")
            }
            console.printLine("Book #${index + 1}")
            console.printLine("Tittle: ${book.tittle}")
            console.printLine("Author: ${book.author}")
            console.printLine("Publisher: ${book.publishingCompany}")
        }
    }

    fun present(header: String, books: List<Book>) {
        console.printLine(header)
        present(books)
    }
}