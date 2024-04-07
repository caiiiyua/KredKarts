package com.caiiiyua.kredkarts.ui

import com.caiiiyua.kredkarts.data.CreditCardsRepository
import com.caiiiyua.kredkarts.data.entity.CreditCard
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private val creditCardsRepository: CreditCardsRepository = mockk()
    private val coroutineScopeProvider: CoroutineScopeProvider = mockk()

    @Before
    fun setup() {

    }

    @Test
    fun `Observe uiState without credit cards from repository`() = runTest {
        //Given
        coEvery { coroutineScopeProvider.provideCoroutineScope(any()) } returns CoroutineScope(Dispatchers.Unconfined)
        coEvery { creditCardsRepository.getCreditCards() } returns flowOf(emptyList())
        viewModel = MainViewModel(creditCardsRepository, coroutineScopeProvider)

        //When
        val uiState = viewModel.uiState.value

        //Then
        assertEquals(true, uiState.creditCards.isEmpty())
        assertEquals(false, uiState.loading)
    }

    @Test
    fun `Observe uiState with credit cards from repository`() = runTest {
        //Given
        coEvery { coroutineScopeProvider.provideCoroutineScope(any()) } returns CoroutineScope(Dispatchers.Unconfined)
        coEvery { creditCardsRepository.getCreditCards() } returns flowOf(creditCards)
        viewModel = MainViewModel(creditCardsRepository, coroutineScopeProvider)

        //When
        val uiState = viewModel.uiState.value

        //Then
        assertEquals(creditCards, uiState.creditCards)
        assertEquals(false, uiState.loading)
    }

    companion object {
        private val creditCards = listOf(
            CreditCard(1, "1234-5678-9012-3456", "2023-12", "visa", "12-04-24"),
            CreditCard(2, "2345-6789-0123-4567", "2024-01", "mastercard", "12-04-24")
        )
    }
}