package com.example.bookpedia.book.presentation.book_details

import com.example.bookpedia.book.domain.Book
import com.example.bookpedia.core.presentation.UiText

data class BookDetailsState(
  val isLoading: Boolean = true,
  val isFavorite: Boolean = false,
  val book: Book? = null,
  val errorMessage: UiText? = null
)