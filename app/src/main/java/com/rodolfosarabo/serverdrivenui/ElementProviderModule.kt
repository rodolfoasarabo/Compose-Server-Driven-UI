package com.rodolfosarabo.serverdrivenui

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ElementProviderModule {

    @[Binds Reusable]
    fun bindElementProvider(
        elementProvider: DefaultElementProvider
    ): ElementProvider
}