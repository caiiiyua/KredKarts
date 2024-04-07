package com.caiiiyua.kredkarts.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.caiiiyua.kredkarts.R
import com.caiiiyua.kredkarts.ui.theme.KredKartsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KredKartsApp(
    uiState: KredKartsUiState,
    onLoadMore: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary

                ),
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) { innerPadding ->
        val navController = rememberNavController()

        KredKartsNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            uiState = uiState,
            onLoadMore = onLoadMore
        )
    }

}

@Preview(showBackground = true)
@Composable
fun KredKartsAppPreview() {
    val uiState = PreviewData.provideKredKartsUiState()
    KredKartsTheme {
        KredKartsApp(uiState) {
            // no-op
        }
    }
}