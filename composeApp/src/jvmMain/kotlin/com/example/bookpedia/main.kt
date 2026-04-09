package com.example.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.bookpedia.di.initKoin

fun main() {
  initKoin()
  application {
    Window(
      onCloseRequest = ::exitApplication,
      title = "Bookpedia",
    ) {
      App()
    }
  }
}