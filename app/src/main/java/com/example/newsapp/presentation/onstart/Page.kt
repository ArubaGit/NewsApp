package com.example.newsapp.presentation.onstart

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image:Int
)


val pages = listOf(
    Page(title= "Politics",
        description = "Latest updates on Politics",
        image = R.drawable.politics
),
    Page(title= "Sports",
        description = "Latest updates on Sports",
        image = R.drawable.sports
    ),
    Page(title= "Business",
        description = "Latest updates on Business.. \n& many more",
        image = R.drawable.stocks
    )
)


