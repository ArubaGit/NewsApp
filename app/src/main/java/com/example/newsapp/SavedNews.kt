package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Adapter.SavedNewsAdapter
import com.example.newsapp.Database.Instance
import com.example.newsapp.Database.NewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedNews : AppCompatActivity(),NewsItemClickListener {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SavedNewsAdapter
    private var newsList: MutableList<NewsItem> = mutableListOf() // Initialize as MutableList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_news)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter with empty newsList
        adapter = SavedNewsAdapter(newsList,this)
        recyclerView.adapter = adapter

        // Load news from the database
        getNewsFromDatabase()
    }

    private fun getNewsFromDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            // Obtain an instance of the NewsDatabase
            val newsDatabase = Instance.getDatabase(applicationContext)

            // Perform database operation to get all news items
            val fetchedNewsList = newsDatabase.newsItemDao().getAllNewsItems()

            Log.d("newss", fetchedNewsList.toString())

            // Update UI on the main thread
            launch(Dispatchers.Main) {
                // Clear existing newsList and add fetched items
                newsList.clear()
                newsList.addAll(fetchedNewsList)
                // Notify the adapter that the dataset has changed
                adapter.notifyDataSetChanged()
            }
        }
    }
    override fun onItemClick(url: String) {
        val intent = Intent(this, NewsUI::class.java)
        intent.putExtra("NEWS_URL", url)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Instead of calling super.onBackPressed(), start the home screen activity
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
        finish() // Finish this activity to prevent it from going back when pressing back again
    }
}
//class SavedNews : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: SavedNewsAdapter
//    private var newsList: MutableList<NewsItem> = mutableListOf()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_saved_news)
//
//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        adapter = SavedNewsAdapter(this, newsList)
//        recyclerView.adapter = adapter
//
//        getNewsFromDatabase()
//    }
//
//    private fun getNewsFromDatabase() {
//        lifecycleScope.launch(Dispatchers.IO) {
//            try {
//                val newsDatabase = Instance.getDatabase(applicationContext)
//                val fetchedNewsList = newsDatabase.newsItemDao().getAllNewsItems()
//
//                Log.d("newss", fetchedNewsList.toString())
//
//                withContext(Dispatchers.Main) {
//                    newsList.clear()
//                    newsList.addAll(fetchedNewsList)
//                    adapter.notifyDataSetChanged()
//                }
//            } catch (e: Exception) {
//                Log.e("SavedNews", "Error fetching news from database: ${e.message}", e)
//            }
//        }
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        val intent = Intent(this, HomeScreenActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
//}
