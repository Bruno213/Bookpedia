package com.example.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.example.bookpedia.book.data.repository.DefaultBookRepository
import com.example.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.example.bookpedia.book.presentation.book_list.BookListViewModel
import com.example.bookpedia.core.data.HttpClientFactory

@Composable
@Preview
fun App() {
    MaterialTheme {
        BookListScreenRoot(
            viewModel = remember { BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDataSource(
                        httpClient = HttpClientFactory.create(httpClientEngine())
                    )
                )
            ) },
            onBookClick = {},
            modifier = Modifier
        )
    }
}