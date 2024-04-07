package com.caiiiyua.kredkarts.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.caiiiyua.kredkarts.carddetails.ui.cardDetailsScreen
import com.caiiiyua.kredkarts.home.ui.homeScreen

@Composable
fun KredKartsNavHost(
    navController: NavHostController,
    uiState: KredKartsUiState,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = KredKartsRoute.Home.route
    ) {
        homeScreen(uiState, onCardClicked = {
            navController.navigate(KredKartsRoute.createRouteWithCardId(it.id))
        }, onLoadMore = onLoadMore)
        cardDetailsScreen()
    }
}