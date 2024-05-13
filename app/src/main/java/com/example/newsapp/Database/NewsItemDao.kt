package com.example.newsapp.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsItemDao {



    @Query("DELETE FROM news")
    suspend fun deleteAllNewsItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsItem(newsItem: NewsItem): Long

    @Query("SELECT * FROM news")
    suspend fun getAllNewsItems(): List<NewsItem>

}