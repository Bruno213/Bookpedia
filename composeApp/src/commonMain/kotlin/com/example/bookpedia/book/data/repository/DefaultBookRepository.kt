package com.example.bookpedia.book.data.repository

import com.example.bookpedia.book.data.mappers.toBook
import com.example.bookpedia.book.data.network.RemoteBookDataSource
import com.example.bookpedia.book.domain.Book
import com.example.bookpedia.book.domain.BookRepository
import com.example.bookpedia.core.domain.DataError
import com.example.bookpedia.core.domain.Result
import com.example.bookpedia.core.domain.map

class DefaultBookRepository(
  private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {
  override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
    return remoteBookDataSource
      .searchBooks(query)
      .map { dto ->
        dto.results.map { it.toBook() }
      }
  }
}