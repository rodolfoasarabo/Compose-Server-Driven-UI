package com.rodolfosarabo.serverdrivenui

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Reusable
    fun bindsComponentDataSource(
        componentsDataSourceImpl: ComponentsDataSourceImpl
    ): ComponentsDataSource
}