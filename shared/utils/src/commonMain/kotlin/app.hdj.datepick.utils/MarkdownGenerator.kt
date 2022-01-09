package app.hdj.datepick.utils

class MarkdownGenerator {

    class Builder {

        private var content : String = ""

        fun appendImage(imageUrl: String, caption : String? = null) : Builder {
            content += "![$caption]('$imageUrl')\n"
            return this
        }

        fun appendText(text: String) : Builder {
            content += "$text\n\n"
            return this
        }

        fun build() = content

    }

}