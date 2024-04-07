package com.caiiiyua.kredkarts.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caiiiyua.kredkarts.data.entity.CreditCard
import com.caiiiyua.kredkarts.home.ui.component.CreditCardItem
import com.caiiiyua.kredkarts.home.ui.component.LoadMoreItem
import com.caiiiyua.kredkarts.ui.PreviewData
import com.caiiiyua.kredkarts.ui.theme.KredKartsTheme

@Composable
fun HomeScreen(
    creditCards: List<CreditCard>,
    onCardClicked: (CreditCard) -> Unit = {},
    onLoadMore: () -> Unit = {}
) {
    val listState = rememberLazyListState()

    // Observe scroll position
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastIndex ->
                // Trigger load more when we've scrolled to the last item
                if (lastIndex != null && lastIndex >= creditCards.size - 1) {
                    onLoadMore()
                }
            }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (creditCards.isEmpty()) {
            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(32.dp),
                onClick = onLoadMore,
            ) {
                Text(text = "Initialize Data")
            }
        } else {
            LazyColumn {
                items(creditCards.size) { creditCard ->
                    CreditCardItem(
                        creditCard = creditCards[creditCard],
                        onCardClicked = onCardClicked
                    )
                }
                item {
                    LoadMoreItem(onLoadMore = onLoadMore)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val creditCards = PreviewData.provideCreditCards()
    KredKartsTheme {
        HomeScreen(creditCards)
    }
}