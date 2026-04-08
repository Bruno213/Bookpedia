package com.example.bookpedia.book.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
  @SerialName("docs") val results: List<SearchBookDto>
)