package com.caiiiyua.kredkarts.carddetails.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.caiiiyua.kredkarts.ui.KredKartsRoute

fun NavGraphBuilder.cardDetailsScreen() {
    composable(
        KredKartsRoute.CreditCardDetails.route,
        arguments = listOf(navArgument("cardId") { type = NavType.StringType })
    ) { it ->
        CardDetailsScreen(cardId = it.arguments?.getString("cardId") ?: "0")
    }
}