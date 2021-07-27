package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class FilterTag(
    override val id: String,
    val name: String
) : Id

fun fakeFilterTag1() = FilterTag(Random.nextInt().toString(), "코로나19 안전한")
fun fakeFilterTag2() = FilterTag(Random.nextInt().toString(), "분위기좋은")
fun fakeFilterTag3() = FilterTag(Random.nextInt().toString(), "조용한")
fun fakeFilterTag4() = FilterTag(Random.nextInt().toString(), "친절한")

val fakeFilterTagList = listOf(
    fakeFilterTag1(),
    fakeFilterTag2(),
    fakeFilterTag3(),
    fakeFilterTag4()
)