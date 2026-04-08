package com.example.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.example.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }