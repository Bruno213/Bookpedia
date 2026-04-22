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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel(
  private val bookRepository: BookRepository,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val bookId = savedStateHandle.toRoute<Route.BookDetail>().bookId

  private val _state = MutableStateFlow(BookDetailsState())
  val state = _state
    .onStart {
      fetchBookDescription()
      observeFavoriteStatus()
    }
    .stateIn(
      viewModelScope,
      SharingStarted.WhileSubscribed(5000),
      BookDetailsState()
    )

  fun onAction(action: BookDetailsAction) {
    when (action) {
      BookDetailsAction.OnBackClick -> {}
      BookDetailsAction.OnFavoriteClick -> {
        viewModelScope.launch {
          if(_state.value.isFavorite) {
            bookRepository.unmarkAsFavorite(bookId)
          } else {
            _state.value.book?.let { bookRepository.markAsFavorite(it) }
          }
        }
      }
      is BookDetailsAction.OnSelectedBookChange -> {
        _state.update {
          it.copy(
            book = action.book
          )
        }
      }
    }
  }

  private fun observeFavoriteStatus() {
    bookRepository
      .isBookFavorite(bookId)
      .onEach { isFavorite ->
        _state.update { it.copy(isFavorite = isFavorite) }
      }
      .launchIn(viewModelScope)
  }

  private fun fetchBookDescription() {
    viewModelScope.launch {
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