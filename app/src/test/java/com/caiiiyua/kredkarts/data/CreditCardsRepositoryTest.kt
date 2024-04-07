package com.caiiiyua.kredkarts.data

import com.caiiiyua.kredkarts.data.local.CreditCardsDataSource
import com.caiiiyua.kredkarts.data.network.CreditCardDto
import com.caiiiyua.kredkarts.data.network.KredKartsNetworkService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CreditCardsRepositoryTest {
    private lateinit var repository: CreditCardsRepository
    private val dataSource: CreditCardsDataSource = mockk()
    private val networkService: KredKartsNetworkService = mockk()

    @Before
    fun setup() {
        repository = CreditCardsRepositoryImpl(dataSource, networkService)
    }

    @Test
    fun `getCreditCards returns data from dataSource`(): Unit = runTest {
        val creditCards = listOf(
            CreditCardDto(1, "1234-5678-9012-3456", "2023-12", "visa", "12-04-24"),
            CreditCardDto(2, "2345-6789-0123-4567", "2024-01", "mastercard", "12-04-24")
        )
        coEvery { dataSource.getCreditCards() } returns flowOf(creditCards)

        val result = repository.getCreditCards().first()

        assertEquals(creditCards.size, result.size)
    }

    @Test
    fun `loadMoreCreditCards fetches data from network and adds to dataSource`(): Unit = runTest {
        val creditCards = listOf(
            CreditCardDto(3, "3456-7890-1234-5678", "2025-02", "amex", "12-04-24"),
            CreditCardDto(4, "4567-8901-2345-6789", "2026-03", "discover", "12-04-24")
        )
        coEvery { networkService.getCreditCards(any()) } returns creditCards
        coEvery { dataSource.addCreditCards(creditCards) } returns Unit

        repository.loadMoreCreditCards()

        coEvery { dataSource.getCreditCards() } returns flowOf(creditCards)
        val result = repository.getCreditCards().first()

        assertEquals(creditCards.size, result.size)
    }
}