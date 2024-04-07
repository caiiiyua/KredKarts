package com.caiiiyua.kredkarts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeProvider {
    fun provideCoroutineScope(viewModel: ViewModel): CoroutineScope
}

class ViewModelScopeProvider : CoroutineScopeProvider {
    override fun provideCoroutineScope(viewModel: ViewModel): CoroutineScope {
        return viewModel.viewModelScope
    }
}