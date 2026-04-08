package com.example.bookpedia.book.data.mappers

import com.example.bookpedia.book.data.SearchBookDto
import com.example.bookpedia.book.domain.Book

fun SearchBookDto.toBook(): Book {
  return Book(
    id = id,
    title = title,
    imageUrl = if (coverKey != null) "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
    else "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg",
    authors = authorNames ?: emptyList(),
    description = null,
    languages = languages ?: emptyList(),
    firstPublishYear = firstPublishYear.toString(),
    averageRating = ratingsAverage,
    ratingCount = ratingsCount,
    numPages = numPagesMedian,
    numEditions = numEditions ?: 0
  )
}