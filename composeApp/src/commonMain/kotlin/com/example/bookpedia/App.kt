package com.example.bookpedia

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.bookpedia.app.Route
import com.example.bookpedia.book.presentation.SelectedBookViewModel
import com.example.bookpedia.book.presentation.book_details.BookDetailsAction
import com.example.bookpedia.book.presentation.book_details.BookDetailsScreenRoot
import com.example.bookpedia.book.presentation.book_details.BookDetailsViewModel
import com.example.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.example.bookpedia.book.presentation.book_list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
  MaterialTheme {
    val navController = rememberNavController()

    NavHost(
      navController = navController,
      startDestination = Route.BookGraph
    ) {
      navigation<Route.BookGraph>(
        startDestination = Route.BookList,
      ) {
        composable<Route.BookList>(
          exitTransition = { slideOutHorizontally() },
          popEnterTransition = { slideInHorizontally() }
        ) {
          val viewModel = koinViewModel<BookListViewModel>()
          val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)

          LaunchedEffect(true) {
            selectedBookViewModel.onSelected(null)
          }

          BookListScreenRoot(
            viewModel = viewModel,
            onBookClick = { book ->
              selectedBookViewModel.onSelected(book)
              navController.navigate(Route.BookDetail(book.id))
            },
          )
        }

        composable<Route.BookDetail>(
          enterTransition = { slideInHorizontally { initialOffset -> initialOffset } },
          popExitTransition = { slideOutHorizontally { targetOffset -> -targetOffset } }
        ) {
          val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)
          val viewModel = koinViewModel<BookDetailsViewModel>()
          val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

          LaunchedEffect(selectedBook) {
            selectedBook?.let {
              viewModel.onAction(BookDetailsAction.OnSelectedBookChange(it))
            }
          }

          BookDetailsScreenRoot(
            viewModel = viewModel,
            onBackClick = {
              navController.navigateUp()
            }
          )
        }
      }
    }
  }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
  navController: NavController
): T {
  val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()

  val parentEntry = remember(this) {
    navController.getBackStackEntry(navGraphRoute)
  }

  return koinViewModel(
    viewModelStoreOwner = parentEntry
  )
}