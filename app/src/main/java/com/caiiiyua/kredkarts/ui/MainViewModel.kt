package com.caiiiyua.kredkarts.ui

import androidx.lifecycle.ViewModel
import com.caiiiyua.kredkarts.data.CreditCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val creditCardsRepository: CreditCardsRepository,
    private val coroutineScopeProvider: CoroutineScopeProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow(KredKartsUiState(loading = true))
    val uiState: StateFlow<KredKartsUiState> = _uiState

    fun loadMoreCreditCards() {
        coroutineScopeProvider.provideCoroutineScope(this).launch {
            creditCardsRepository.loadMoreCreditCards()
        }
    }

    init {
        fetchCreditCards()
    }

    private fun fetchCreditCards() {
        coroutineScopeProvider.provideCoroutineScope(this).launch {
            creditCardsRepository.getCreditCards()
                .catch { e ->
                    _uiState.value = KredKartsUiState(error = e.message, loading = false)
                }
                .collect { creditCards ->
                    _uiState.value = KredKartsUiState(creditCards = creditCards, loading = false)
                }
        }
    }
}