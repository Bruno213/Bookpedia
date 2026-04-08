package com.example.bookpedia.book.domain

import com.example.bookpedia.core.domain.DataError
import com.example.bookpedia.core.domain.Result

interface BookRepository {
  suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}