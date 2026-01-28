package com.example.japanese_android.ui.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun calculateScrollDirection(
    listSate: LazyListState
) : Boolean{
    var previousIndex by remember { mutableStateOf(0) }
    var previousOffset by remember { mutableStateOf(0) }

    val isScrollUp by remember {
        derivedStateOf {
            val scrollingUp =
                if (listSate.firstVisibleItemIndex != previousIndex)
                    listSate.firstVisibleItemIndex < previousIndex
                else
                    listSate.firstVisibleItemScrollOffset < previousOffset

            previousIndex = listSate.firstVisibleItemIndex
            previousOffset = listSate.firstVisibleItemScrollOffset

            scrollingUp
        }

    }

    return isScrollUp
}