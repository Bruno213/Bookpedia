package com.example.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.example.bookpedia.book.presentation.book_list.BookListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = koinViewModel<BookListViewModel>()
        BookListScreenRoot(
            viewModel = viewModel,
            onBookClick = {},
            modifier = Modifier
        )
    }
}