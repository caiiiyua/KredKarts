package com.caiiiyua.kredkarts.ui

import com.caiiiyua.kredkarts.data.entity.CreditCard

class PreviewData {
    companion object {

        fun provideKredKartsUiState(): KredKartsUiState {
            return KredKartsUiState(
                creditCards = provideCreditCards(),
                loading = false,
                error = null
            )
        }
        fun provideCreditCards(): List<CreditCard> {
            val cards = listOf(
                CreditCard(1, "1", "1234 5678 9012 3456", "12/23", "VISA"),
                CreditCard(2, "2", "1234 5678 9012 3457", "12/23", "Mastercard"),
                CreditCard(3, "3", "1234 5678 9012 3458", "12/23", "AMEX"),
                CreditCard(4, "4", "1234 5678 9012 3459", "12/23", "VISA"),
            )
            return cards
        }

        fun provideCreditCard(): CreditCard {
            return CreditCard(1, "1", "1234 5678 9012 3456", "12/23", "VISA")
        }
    }
}