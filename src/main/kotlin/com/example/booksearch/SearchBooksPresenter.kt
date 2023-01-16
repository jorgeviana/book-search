package com.example.booksearch

abstract class BooksPresenter(private val console: Console) {
    abstract fun present(books: List<Book>)

    fun defaultPresentation(books: List<Book>) {
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

class SearchBooksPresenter(private val console: Console) : BooksPresenter(console) {

    override fun present(books: List<Book>) {
        defaultPresentation(books)
    }
}

class ReadingListPresenter(private val console: Console) : BooksPresenter(console) {

    override fun present(books: List<Book>) {
        if (books.isEmpty()) {
            console.printLine("- no books in the reading list")
            return
        }
        console.printLine("The reading list is:")
        defaultPresentation(books)
    }
}