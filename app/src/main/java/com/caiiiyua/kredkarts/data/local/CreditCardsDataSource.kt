package com.caiiiyua.kredkarts.data.local

import com.caiiiyua.kredkarts.data.network.CreditCardDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface CreditCardsDataSource {
    fun getCreditCards(): Flow<List<CreditCardDto>>
    fun addCreditCard(creditCard: CreditCardDto)
    suspend fun addCreditCards(creditCards: List<CreditCardDto>)
}

class CreditCardsLocalDataSource : CreditCardsDataSource {
    // This is a simple in-memory implementation of a local data source
    private val creditCardsStateFlow = MutableStateFlow(emptyList<CreditCardDto>())

    override fun getCreditCards(): Flow<List<CreditCardDto>> {
        return creditCardsStateFlow
    }

    override fun addCreditCard(creditCard: CreditCardDto) {
        creditCardsStateFlow.update {
            it + creditCard
        }
    }

    override suspend fun addCreditCards(creditCards: List<CreditCardDto>) {
        creditCardsStateFlow.update {
            it + creditCards
        }
    }
}