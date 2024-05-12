package com.example.newsapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.API.NewsApi
import com.example.newsapp.API.newsService
import com.example.newsapp.Location.GetLocation
import com.example.newsapp.databinding.ActivityHomeScreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding
    private val newsAdapter = NewsAdapter()
    private lateinit var loc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Create an object of GetLocation
        val getLocation = GetLocation(this)
        // Call getLocation function
        //getLocation.getLocation()
       // loc = getLocation.getLocation().toString()?.lowercase()?: ""
        loc = getLocation.getLocation().toString()?:""

        Log.d("Location",loc)

        // Find buttons by their IDs
        val generalButton = findViewById<Button>(R.id.generalButton)
        val businessButton = findViewById<Button>(R.id.businessButton)
        val technologyButton = findViewById<Button>(R.id.technologyButton)
        val entertainmentButton=findViewById<Button>(R.id.entertainmentButton)
        val healthButton=findViewById<Button>(R.id.healthButton)
        val scienceButton=findViewById<Button>(R.id.scienceButton)
        val sportsButton=findViewById<Button>(R.id.sportsButton)

        fetchNewsData("", "ab1fa4fdcd2e4c2891a18238f62de9f7", loc)
        generalButton.setOnClickListener { fetchNewsData("General", "ab1fa4fdcd2e4c2891a18238f62de9f7", loc) }
        businessButton.setOnClickListener { fetchNewsData("Business", "ab1fa4fdcd2e4c2891a18238f62de9f7", loc) }
        entertainmentButton.setOnClickListener { fetchNewsData("Entertainment", "ab1fa4fdcd2e4c2891a18238f62de9f7", loc) }
        healthButton.setOnClickListener { fetchNewsData("Health", "ab1fa4fdcd2e4c2891a18238f62de9f7", loc) }
        scienceButton.setOnClickListener { fetchNewsData("Science", "ab1fa4fdcd2e4c2891a18238f62de9f7", loc) }
        sportsButton.setOnClickListener { fetchNewsData("Sports", "ab1fa4fdcd2e4c2891a18238f62de9f7", loc) }
        technologyButton.setOnClickListener { fetchNewsData("Technology", "ab1fa4fdcd2e4c2891a18238f62de9f7", loc) }


    }

    private fun setupRecyclerView() {
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeScreenActivity)
            adapter = newsAdapter
        }
    }

    private fun fetchNewsData(cat: String, key: String, country: String) {
        val call = newsService.newsInstance.getNewsData(category = cat, key = key, country = country)

        call.enqueue(object : Callback<NewsApi> {
            override fun onResponse(call: Call<NewsApi>, response: Response<NewsApi>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    articles?.let {
                        newsAdapter.setData(it)
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<NewsApi>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
