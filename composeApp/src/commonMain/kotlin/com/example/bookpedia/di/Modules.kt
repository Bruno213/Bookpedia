package com.example.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.bookpedia.book.data.database.BookDatabase
import com.example.bookpedia.book.data.database.DatabaseFactory
import com.example.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.example.bookpedia.book.data.network.RemoteBookDataSource
import com.example.bookpedia.book.data.repository.DefaultBookRepository
import com.example.bookpedia.book.domain.BookRepository
import com.example.bookpedia.book.presentation.SelectedBookViewModel
import com.example.bookpedia.book.presentation.book_details.BookDetailsViewModel
import com.example.bookpedia.book.presentation.book_list.BookListViewModel
import com.example.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
  single { HttpClientFactory.create(get()) }

  singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
  singleOf(::DefaultBookRepository).bind<BookRepository>()

  single {
    get<DatabaseFactory>().create()
      .setDriver(BundledSQLiteDriver())
      .build()
  }

  single { get<BookDatabase>().favoriteBooksDao }

  viewModelOf(::BookListViewModel)
  viewModelOf(::BookDetailsViewModel)
  viewModelOf(::SelectedBookViewModel)
}

expect val platformModule: Module