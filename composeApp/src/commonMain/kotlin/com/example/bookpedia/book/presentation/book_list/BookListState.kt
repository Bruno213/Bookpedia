package com.example.bookpedia.book.presentation.book_list

import com.example.bookpedia.book.domain.Book
import com.example.bookpedia.core.presentation.UiText

data class BookListState(
  val searchQuery: String = "",
  val searchResults: List<Book> = emptyList(),
  val favoriteBooks: List<Book> = emptyList(),
  val isLoading: Boolean = false,
  val selectedTabIndex: Int = 0,
  val errorMessage: UiText? = null
)

val books = (1 .. 100).map {
  Book(
    id = it.toString(),
    imageUrl = "https://test.com",
    title = "Book $it",
    authors = listOf("OEReaper"),
    description = "Description $it",
    languages = emptyList(),
    firstPublishYear = null,
    averageRating = 4.6354354,
    ratingCount = 5,
    numPages = 100,
    numEditions = 3
  )
}
