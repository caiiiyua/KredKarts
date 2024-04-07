package com.caiiiyua.kredkarts.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.caiiiyua.kredkarts.data.entity.CreditCard
import com.caiiiyua.kredkarts.ui.KredKartsRoute
import com.caiiiyua.kredkarts.ui.KredKartsUiState

fun NavGraphBuilder.homeScreen(
    uiState: KredKartsUiState,
    onCardClicked: (CreditCard) -> Unit,
    onLoadMore: () -> Unit
) {
    composable(KredKartsRoute.Home.route) {
        HomeScreen(
            creditCards = uiState.creditCards,
            onCardClicked = onCardClicked,
            onLoadMore = onLoadMore
        )
    }
}