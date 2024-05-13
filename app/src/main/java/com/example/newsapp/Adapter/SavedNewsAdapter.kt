package com.example.newsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Database.NewsItem
import com.example.newsapp.R
import com.example.newsapp.SavedNews

interface NewsItemClickListener {
    fun onItemClick(url: String)
}
//class SavedNewsAdapter(private val newsList: List<NewsItem>) : RecyclerView.Adapter<SavedNewsAdapter.ViewHolder>() {
class SavedNewsAdapter(
    private val newsList: List<NewsItem>,
    private val itemClickListener: SavedNews
) : RecyclerView.Adapter<SavedNewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_news_layout, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.textTitle.text = newsItem.title
        holder.textDescription.text = newsItem.description

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(newsItem.url)
        }

//        holder.itemView.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.url))
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}

//class SavedNewsAdapter(
//    private val context: Context,
//    private val newsList: List<NewsItem>
//) : RecyclerView.Adapter<SavedNewsAdapter.ViewHolder>() {
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
//        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_news_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val newsItem = newsList[position]
//        holder.textTitle.text = newsItem.title
//        holder.textDescription.text = newsItem.description
//
//        holder.itemView.setOnClickListener {
//            newsItem.url?.let { url ->
//                openWebView(url)
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return newsList.size
//    }
//
//    private fun openWebView(url: String) {
//        val intent = Intent(context, WebViewActivity::class.java).apply {
//            putExtra("url", url)
//        }
//        context.startActivity(intent)
//    }
//}
