package com.caiiiyua.kredkarts.carddetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caiiiyua.kredkarts.data.CreditCardsRepository
import com.caiiiyua.kredkarts.data.entity.CreditCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    creditCardsRepository: CreditCardsRepository
) : ViewModel() {
    private val cardId: MutableStateFlow<String> = MutableStateFlow("0")

    val uiState: StateFlow<CardDetailsUiState> = combine(
        cardId,
        creditCardsRepository.getCreditCards(),
        (::mapToUiState)
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CardDetailsUiState.CardDetailsLoading
    )

    fun loadCardDetails(cardId: String) {
        this.cardId.value = cardId
    }

    private fun mapToUiState(
        cardId: String,
        creditCards: List<CreditCard>
    ): CardDetailsUiState {
        val creditCard = creditCards.find { it.id == cardId.toInt() }
        return if (creditCard != null) {
            CardDetailsUiState.CardDetailsLoaded(creditCard)
        } else {
            CardDetailsUiState.Empty
        }
    }
}

sealed interface CardDetailsUiState {
    data object CardDetailsLoading : CardDetailsUiState

    data class CardDetailsLoaded(
        val creditCard: CreditCard
    ) : CardDetailsUiState

    data object Empty : CardDetailsUiState
}