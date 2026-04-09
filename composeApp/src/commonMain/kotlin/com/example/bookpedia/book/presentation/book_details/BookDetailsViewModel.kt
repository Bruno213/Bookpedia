package com.example.bookpedia.book.presentation.book_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.bookpedia.app.Route
import com.example.bookpedia.book.domain.BookRepository
import com.example.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel(
  private val bookRepository: BookRepository,
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val _state = MutableStateFlow(BookDetailsState())
  val state = _state
    .onStart {
      fetchBookDescription()
    }
    .stateIn(
      viewModelScope,
      SharingStarted.WhileSubscribed(5000),
      BookDetailsState()
    )

  fun onAction(action: BookDetailsAction) {
    when (action) {
      BookDetailsAction.OnBackClick -> {}
      BookDetailsAction.OnFavoriteClick -> {}
      is BookDetailsAction.OnSelectedBookChange -> {
        _state.update {
          it.copy(
            book = action.book
          )
        }
      }
    }
  }

  private fun fetchBookDescription() {
    viewModelScope.launch {
      val bookId = savedStateHandle.toRoute<Route.BookDetail>().bookId
      bookRepository
        .getBookDescription(bookId)
        .onSuccess { description ->
          _state.update { it.copy(
            book = it.book?.copy(
              description = description
            ),
            isLoading = false
          ) }
        }
    }
  }
}