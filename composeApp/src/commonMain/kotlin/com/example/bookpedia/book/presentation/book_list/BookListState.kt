package com.example.bookpedia.book.presentation.book_list

import com.example.bookpedia.book.domain.Book
import com.example.bookpedia.core.presentation.UiText

data class BookListState(
  val searchQuery: String = "",
  val searchResults: List<Book> = emptyList(),
  val favoriteBooks: List<Book> = emptyList(),
  val isLoading: Boolean = true,
  val selectedTabIndex: Int = 0,
  val errorMessage: UiText? = null
)
