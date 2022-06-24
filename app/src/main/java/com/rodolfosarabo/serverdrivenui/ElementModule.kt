package com.rodolfosarabo.serverdrivenui

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface ElementModule {

    @[Binds Reusable IntoSet]
    fun bindTextFieldElement(
        textFieldElement: TextFieldElement
    ): ComposableElement<*>

    @[Binds Reusable IntoSet]
    fun bindTextElement(
        textElement: TextElement
    ): ComposableElement<*>

    @[Binds Reusable IntoSet]
    fun bindImageElement(
        imageElement: ImageElement
    ): ComposableElement<*>

    @[Binds Reusable IntoSet]
    fun bindButtonElement(
        buttonElement: ButtonElement
    ): ComposableElement<*>
}