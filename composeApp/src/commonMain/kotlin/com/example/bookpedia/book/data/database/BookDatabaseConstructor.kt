package com.example.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor

/*
* Room compiler generates the platform-specific implementation for the database automatically
* The IDE still complains about missing actuals, so we need to suppress the warning
* */
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<BookDatabase> {
  override fun initialize(): BookDatabase
}