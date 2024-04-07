package com.caiiiyua.kredkarts.ui

enum class KredKartsRoute(val route: String) {
    Home("home"),
    CreditCardDetails("card_details/{cardId}");

    companion object {
        fun createRouteWithCardId(cardId: Int): String {
            return "card_details/$cardId"
        }
    }
}