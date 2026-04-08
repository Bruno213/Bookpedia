package com.example.bookpedia

import android.app.Application
import com.example.bookpedia.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication: Application() {

  override fun onCreate() {
    super.onCreate()

    initKoin {
      // Required by Koin only on Android so that it can construct dependencies that require Context.
      androidContext(this@BookApplication)
    }
  }
}