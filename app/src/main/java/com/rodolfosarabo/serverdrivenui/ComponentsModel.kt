package com.rodolfosarabo.serverdrivenui

data class ComponentsModel(
    val cards: List<CardModel>
)

data class TextModel(
    override val identifier: String = "TEXT",
    override val content: TextContentModel
) : CardModel

data class TextFieldModel(
    override val identifier: String = "TEXT_FIELD",
    override val content: CardContentModel
) : CardModel

data class TextContentModel(
    val label: String
) : CardContentModel

data class ImageModel(
    override val identifier: String = "IMAGE",
    override val content: ImageContentModel
) : CardModel

data class ButtonModel(
    override val identifier: String = "BUTTON",
    override val content: ButtonContentModel
) : CardModel

data class ImageContentModel(
    val imageUrl: String
) : CardContentModel

data class TextFieldContentModel(
    val fieldName: String
) : CardContentModel

data class ButtonContentModel(
    val label: String
) : CardContentModel

interface CardModel {
    val identifier: String
    val content: CardContentModel
}

interface CardContentModel

object Components {

    fun makeComponents(): ComponentsModel {
        return ComponentsModel(
            listOf(
                makeTextModel("Hello"),
                makeTextModel("Server Driven UI"),
                makeTextModel("Compose"),
                makeImageModel(),
                makeTextField("first_field"),
                makeTextField("second_field"),
                makeButtonModel()
            )
        )
    }

    fun makeTextModel(label: String = "Texto de exemplo"): CardModel {
        return TextModel(
            content = TextContentModel(label)
        )
    }

    fun makeImageModel(
        url: String = "https://s2.glbimg.com/JHS8-aiceiRjnBWDwpLJMqh-FVw=/0x0:3520x2298/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_bc8228b6673f488aa253bbcb03c80ec5/internal_photos/bs/2022/K/v/BJ321rQrCSbBBTvWEUAQ/gettyimages-1398576484.jpg"
    ): CardModel {
        return ImageModel(
            content = ImageContentModel(url)
        )
    }

    fun makeTextField(fieldName: String = "value"): CardModel {
        return TextFieldModel(
            content = TextFieldContentModel(fieldName)
        )
    }

    fun makeButtonModel(label: String = "Confirm"): CardModel {
        return ButtonModel(
            content = ButtonContentModel(label)
        )
    }
}