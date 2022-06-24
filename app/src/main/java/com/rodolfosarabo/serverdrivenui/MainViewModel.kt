package com.rodolfosarabo.serverdrivenui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSource: ComponentsDataSource,
    private val elementProvider: ElementProvider
) : ViewModel() {

    val componentsFlow =
        MutableStateFlow<List<Pair<CardContentModel, ComposableElement<CardContentModel>?>>>(
            emptyList()
        )

    init {
        viewModelScope.launch {
            val components = dataSource.getComponents()

            val elementList = components.cards.map {
                elementProvider.provide(it)
            }

            componentsFlow.value = elementList
        }
    }
}