package com.rodolfosarabo.serverdrivenui

import javax.inject.Inject

interface ElementProvider {
    fun provide(content: CardModel): Pair<CardContentModel, ComposableElement<CardContentModel>?>
}


class DefaultElementProvider @Inject constructor(
    private val composableElementSet: Set<@JvmSuppressWildcards ComposableElement<*>>
) : ElementProvider {

    private val composableElementMap: Map<String, ComposableElement<CardContentModel>> by lazy {
        populateMap()
    }

    override fun provide(content: CardModel): Pair<CardContentModel, ComposableElement<CardContentModel>?> {
        return Pair(content.content, composableElementMap[content.identifier])
    }

    private fun populateMap(): Map<String, ComposableElement<CardContentModel>> {
        val filteredElements = composableElementSet.distinctBy { it.identifier }

        @Suppress("UNCHECKED_CAST")
        return filteredElements.associateBy {
            it.identifier
        } as Map<String, ComposableElement<CardContentModel>>
    }
}