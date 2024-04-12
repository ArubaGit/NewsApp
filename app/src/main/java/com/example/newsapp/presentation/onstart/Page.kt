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
        description = "Description of Politics, Description of Politics, Description of Politics",
        image = R.drawable.politics
),
    Page(title= "Sports",
        description = "Description of Sports, Description of Politics, Description of Politics",
        image = R.drawable.sports
    ),
    Page(title= "Business",
        description = "Description of Politics, Description of Politics, Description of Politics",
        image = R.drawable.stocks
    )
)


