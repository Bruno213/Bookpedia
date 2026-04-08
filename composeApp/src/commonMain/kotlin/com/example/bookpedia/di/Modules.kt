package com.example.bookpedia.di

import com.example.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.example.bookpedia.book.data.network.RemoteBookDataSource
import com.example.bookpedia.book.data.repository.DefaultBookRepository
import com.example.bookpedia.book.domain.BookRepository
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

  viewModelOf(::BookListViewModel)
}

expect val platformModule: Module