package com.caiiiyua.kredkarts.data

import com.caiiiyua.kredkarts.data.local.CreditCardsDataSource
import com.caiiiyua.kredkarts.data.local.CreditCardsLocalDataSource
import com.caiiiyua.kredkarts.data.network.CreditCardDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CreditCardsDataSourceTest {
    private lateinit var dataSource: CreditCardsDataSource

    @Before
    fun setup() {
        dataSource = CreditCardsLocalDataSource()
    }

    @Test
    fun `addCreditCard adds a credit card to the dataSource`() = runTest {
        //Given
        val creditCard = CreditCardDto(3, "3456-7890-1234-5678", "2025-02", "amex", "12-04-24")
//When
        dataSource.addCreditCard(creditCard)
        val result = dataSource.getCreditCards().first()

        assertEquals(listOf(creditCard), result)
    }

    @Test
    fun `addCreditCards adds multiple credit cards to the dataSource`() = runTest {
        //Given
        val creditCards = listOf(
            CreditCardDto(1, "1234-5678-9012-3456", "2023-12", "visa", "12-04-24"),
            CreditCardDto(2, "2345-6789-0123-4567", "2024-01", "mastercard", "12-04-24")
        )
        //When
        dataSource.addCreditCards(creditCards)
        val result = dataSource.getCreditCards().first()

        //Then
        assertEquals(creditCards, result)
    }
}