package com.rodolfosarabo.serverdrivenui

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

private const val FIELD_TYPE = "identifier"

object ComponentAdapterFactory {
    fun get(): JsonAdapter.Factory =
        PolymorphicJsonAdapterFactory.of(CardModel::class.java, FIELD_TYPE)
            .withSubtype(TextModel::class.java, "TEXT")
}