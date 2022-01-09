package app.hdj.datepick.utils

import org.junit.Test
import kotlin.test.assertTrue

class MarkdownGeneratorTest {

    @Test
    fun testBuilder() {

        print(
            MarkdownGenerator.Builder()
                .appendImage("https://sample", "hello")
                .appendText("# 안녕하세요")
                .appendImage("https://sample2", "hello2")
                .appendText("## 안녕하세요")
                .build()
        )

        assertTrue(true)
    }

}