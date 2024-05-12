package com.example.newsapp.API
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsAppInterface {
    @GET("top-headlines")
    fun getNewsData(
        @Query("category") category: String,
        @Query("apiKey") key:String,
        @Query("country") country: String

    ): Call<NewsApi>
}


object newsService{
    val newsInstance: NewsAppInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance=retrofit.create(NewsAppInterface::class.java)

    }
}



//object RetrofitHelper {
//
//    private const val BASE_URL = "https://newsapi.org/v2/"
//
//    fun getInstance(): Retrofit {
//
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//}