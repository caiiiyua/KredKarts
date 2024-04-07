package com.caiiiyua.kredkarts.carddetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caiiiyua.kredkarts.carddetails.ui.component.CreditCardDetails
import com.caiiiyua.kredkarts.ui.PreviewData
import com.caiiiyua.kredkarts.ui.theme.KredKartsTheme

@Composable
fun CardDetailsScreen(
    cardId: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.loadCardDetails(cardId)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is CardDetailsUiState.CardDetailsLoading -> {
                // Loading state
                CircularProgressIndicator()
            }
            is CardDetailsUiState.CardDetailsLoaded -> {
                CreditCardDetails(
                    creditCard = (uiState as CardDetailsUiState.CardDetailsLoaded).creditCard,
                )
            }
            is CardDetailsUiState.Empty -> {
                // Empty state
                throw IllegalStateException("Card not found")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardDetailsPreview() {
    val creditCard = PreviewData.provideCreditCard()
    KredKartsTheme {
        CardDetailsScreen(creditCard.id.toString())
    }
}