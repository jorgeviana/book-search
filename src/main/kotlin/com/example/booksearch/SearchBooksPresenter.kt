package com.example.booksearch

interface BooksPresenter {
    fun present(books: List<Book>)
}

class SearchBooksPresenter(private val console: Console) : BooksPresenter {

    override fun present(books: List<Book>) {
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
}

class ReadingListPresenter(private val console: Console) : BooksPresenter {

    override fun present(books: List<Book>) {
        if (books.isEmpty()) {
            console.printLine("- no books in the reading list")
            return
        }
        console.printLine("The reading list is:")
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
}