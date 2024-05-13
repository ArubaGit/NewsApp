package com.example.newsapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [NewsItem::class], version = 1)
@Database(entities = [NewsItem::class], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsItemDao(): NewsItemDao
}
