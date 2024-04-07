package com.caiiiyua.kredkarts.di

import com.caiiiyua.kredkarts.ui.CoroutineScopeProvider
import com.caiiiyua.kredkarts.ui.ViewModelScopeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
    @Provides
    fun providesViewModelScopeProvider(): CoroutineScopeProvider {
        return ViewModelScopeProvider()
    }
}