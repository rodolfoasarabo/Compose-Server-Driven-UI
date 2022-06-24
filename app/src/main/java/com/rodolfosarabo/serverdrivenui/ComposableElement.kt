package com.rodolfosarabo.serverdrivenui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

interface ComposableElement<Model: CardContentModel> {

    val identifier: String

    @Composable
    fun Compose(
        cardContentModel: Model,
        hoist: Map<String, MutableState<String>>,
    )

    fun getHoist(
        cardContentModel: Model,
    ): Map<String, MutableState<String>>
}