package com.caiiiyua.kredkarts.data

import com.caiiiyua.kredkarts.data.entity.CreditCard
import com.caiiiyua.kredkarts.data.local.CreditCardsDataSource
import com.caiiiyua.kredkarts.data.network.KredKartsNetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CreditCardsRepository {
    fun getCreditCards(): Flow<List<CreditCard>>
    suspend fun loadMoreCreditCards()
}

class CreditCardsRepositoryImpl(
    private val creditCardsDataSource: CreditCardsDataSource,
    private val kredKartsNetworkService: KredKartsNetworkService
) : CreditCardsRepository {
    private var pageState = 0
    override fun getCreditCards(): Flow<List<CreditCard>> {
        return creditCardsDataSource.getCreditCards()
            .map { entities ->
                entities.map(CreditCard::fromDto)
            }
    }

    override suspend fun loadMoreCreditCards() {
        // Load more credit cards from the network
        val creditCards = runCatching {
            kredKartsNetworkService.getCreditCards(++pageState)
        }.getOrDefault(emptyList())
        creditCardsDataSource.addCreditCards(creditCards)
    }
}