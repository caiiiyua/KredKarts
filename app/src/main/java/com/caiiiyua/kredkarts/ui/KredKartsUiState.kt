package com.caiiiyua.kredkarts.ui

import com.caiiiyua.kredkarts.data.entity.CreditCard

data class KredKartsUiState(
    val creditCards: List<CreditCard> = emptyList(),
    val error: String? = null,
    val loading: Boolean = false
)