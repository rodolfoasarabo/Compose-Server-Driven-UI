package com.rodolfosarabo.serverdrivenui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Screen()
        }
    }
}

@Composable
fun Screen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val components = viewModel.componentsFlow.collectAsState()

    LazyColumn {
        items(
            components.value
        ) {
            val hoist = it.second?.getHoist(it.first).orEmpty()
            it.second?.Compose(it.first, hoist)
        }
    }
}

class TextFieldElement @Inject constructor() : ComposableElement<TextFieldContentModel> {

    override val identifier: String = "TEXT_FIELD"

    @Composable
    override fun Compose(
        cardContentModel: TextFieldContentModel,
        hoist: Map<String, MutableState<String>>
    ) {
        TextField(
            value = hoist[cardContentModel.fieldName]?.value.orEmpty(),
            onValueChange = {
                hoist[cardContentModel.fieldName]?.value = it
            }
        )
    }

    override fun getHoist(
        cardContentModel: TextFieldContentModel
    ): Map<String, MutableState<String>> {
        return (cardContentModel as? TextFieldContentModel)?.let {
            mapOf(Pair(it.fieldName, mutableStateOf("")))
        } ?: mapOf()
    }

}

class ButtonElement @Inject constructor(): ComposableElement<ButtonContentModel> {

    override val identifier: String = "BUTTON"

    @Composable
    override fun Compose(
        cardContentModel: ButtonContentModel,
        hoist: Map<String, MutableState<String>>,
    ) {
        Button(onClick = { }) {
            Text(text = cardContentModel.label)
        }
    }

    override fun getHoist(
        cardContentModel: ButtonContentModel
    ): Map<String, MutableState<String>> {
        return mapOf()
    }
}

class TextElement @Inject constructor(): ComposableElement<TextContentModel> {

    override val identifier: String = "TEXT"

    @Composable
    override fun Compose(
        cardContentModel: TextContentModel,
        hoist: Map<String, MutableState<String>>
    ) {
        Text(text = cardContentModel.label)
    }

    override fun getHoist(
        cardContentModel: TextContentModel
    ): Map<String, MutableState<String>> {
        return mapOf()
    }
}

class ImageElement @Inject constructor(): ComposableElement<ImageContentModel> {

    override val identifier: String = "IMAGE"

    @Composable
    override fun Compose(
        cardContentModel: ImageContentModel,
        hoist: Map<String, MutableState<String>>
    ) {
        AsyncImage(model = cardContentModel.imageUrl, contentDescription = null)
    }

    override fun getHoist(
        cardContentModel: ImageContentModel,
    ): Map<String, MutableState<String>> {
        return mapOf()
    }

}

class EmptyElement @Inject constructor(): ComposableElement<CardContentModel> {

    override val identifier: String = "UNKNOWN"

    @Composable
    override fun Compose(
        cardContentModel: CardContentModel,
        hoist: Map<String, MutableState<String>>
    ) {
    }

    override fun getHoist(
        cardContentModel: CardContentModel,
    ): Map<String, MutableState<String>> {
        return mapOf()
    }
}