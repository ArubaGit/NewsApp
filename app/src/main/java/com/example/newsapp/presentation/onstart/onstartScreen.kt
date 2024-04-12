package com.example.newsapp.presentation.onstart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.onstart.Dimesntions.MediumPadding2
import com.example.newsapp.presentation.onstart.common.NewsButton
import com.example.newsapp.presentation.onstart.common.NewsTextButton
import com.example.newsapp.presentation.onstart.components.OnStartPage
import com.example.newsapp.presentation.onstart.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onstartScreen(){

    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                //show the text of the button
                //derivedSateof used because button text will depend on the pages ie page 1, page 2 hai aur agr page3 hai toh it will show get started rather than next
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState) {index->
            OnStartPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding2)
            .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){

            PageIndicator(modifier = Modifier.width(52.dp),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage)


        Row (verticalAlignment = Alignment.CenterVertically) {

            val scope = rememberCoroutineScope()

            if (buttonState.value[0].isNotEmpty()) {
                NewsTextButton(text = buttonState.value[0],
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }

                    })
            }
            NewsButton(text = buttonState.value[1], onClick = {
                scope.launch {
                    if (pagerState.currentPage == 3) {
                        //Navigate to HomeScreen
                    } else {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                }
            })

        }
        }

    }
}