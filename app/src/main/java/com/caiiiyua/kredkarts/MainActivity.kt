package com.caiiiyua.kredkarts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caiiiyua.kredkarts.ui.KredKartsApp
import com.caiiiyua.kredkarts.ui.MainViewModel
import com.caiiiyua.kredkarts.ui.theme.KredKartsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KredKartsTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                KredKartsApp(uiState) {
                    viewModel.loadMoreCreditCards()
                }
            }
        }
    }
}