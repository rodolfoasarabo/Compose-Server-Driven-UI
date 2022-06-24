package com.rodolfosarabo.serverdrivenui

import javax.inject.Inject

interface ComponentsDataSource {

    suspend fun getComponents(): ComponentsModel
}

class ComponentsDataSourceImpl @Inject constructor() : ComponentsDataSource {

    override suspend fun getComponents(): ComponentsModel {
        return Components.makeComponents()
    }
}