package com.example.bookpedia.book.data.network

import com.example.bookpedia.book.data.SearchResponseDto
import com.example.bookpedia.core.domain.DataError
import com.example.bookpedia.core.domain.Result

interface RemoteBookDataSource {
  suspend fun searchBooks(
    query: String,
    resultLimit: Int? = null
  ) : Result<SearchResponseDto, DataError.Remote>
}