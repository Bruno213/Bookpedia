package com.example.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.example.bookpedia.book.presentation.book_list.BookListViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel() },
            onBookClick = {},
            modifier = Modifier
        )
    }
}