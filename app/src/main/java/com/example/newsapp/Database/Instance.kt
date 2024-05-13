//package com.example.newsapp.Database
//
//import android.content.Context
//import androidx.room.Room
//
//class Instance {
//    companion object {
//        lateinit var database: NewsDatabase
//
//        fun giveInstance(context: Context) {
//            database = Room.databaseBuilder(
//                context.applicationContext,
//                NewsDatabase::class.java,
//                "news_database"
//            ).build()
//        }
//    }
//    @Volatile
//    private var database: NewsDatabase? = null
//
//    fun getDatabase(context: Context): NewsDatabase {
//        return database ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                NewsDatabase::class.java,
//                "news_database"
//            ).build()
//            database = instance
//            instance
//        }
//    }
//}
//
//
//
//
//

//package com.example.newsapp.Database
//
//import android.content.Context
//import androidx.room.Room
//
//object Instance {
//    @Volatile
//    var database: NewsDatabase? = null
//
//    fun getDatabase(context: Context): NewsDatabase {
//        return database ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                NewsDatabase::class.java,
//                "news_database"
//            ).build()
//            database = instance
//            instance
//        }
//    }
//
//    fun giveInstance(context: Context) {
//        database = Room.databaseBuilder(
//            context.applicationContext,
//            NewsDatabase::class.java,
//            "news_database"
//        ).build()
//    }
//}
package com.example.newsapp.Database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Instance {
    @Volatile
    var database: NewsDatabase? = null

    private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Migration logic to add the 'url' column to your existing table
            database.execSQL("ALTER TABLE news_item ADD COLUMN url TEXT")
        }
    }

    fun getDatabase(context: Context): NewsDatabase {
        return database ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_database"
            )
                .addMigrations(MIGRATION_1_2) // Add migration from version 1 to 2
                .build()
            database = instance
            instance
        }
    }

    fun giveInstance(context: Context) {
        database = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "news_database"
        )
            .addMigrations(MIGRATION_1_2) // Add migration from version 1 to 2
            .build()
    }
}

