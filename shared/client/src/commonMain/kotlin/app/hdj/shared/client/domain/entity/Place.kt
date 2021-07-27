package app.hdj.shared.client.domain.entity

import app.hdj.shared.client.data.paging.PageData
import app.hdj.shared.client.data.paging.PlatformPagingData
import app.hdj.shared.client.data.paging.createPagingSource
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Place(
    override val id: String,
    val name: String,
    val type: String,
    val lat: Double,
    val lng: Double,
    var cacheExpireAt: Long = 0
) : Id

fun fakePlace1() = Place(
    Random.nextInt().toString(),
    "창화당",
    "음식점",
    lat = 0.0,
    lng = 0.0
)

fun fakePlace2() = Place(
    Random.nextInt().toString(),
    "쿠우쿠우",
    "음식점",
    lat = 0.0,
    lng = 0.0
)

fun fakePlace3() = Place(
    Random.nextInt().toString(),
    "창화당",
    "음식점",
    lat = 0.0,
    lng = 0.0
)

fun fakePlace4() = Place(
    Random.nextInt().toString(),
    "쿠우쿠우",
    "음식점",
    lat = 0.0,
    lng = 0.0
)

val fakePlaceList = listOf(
    fakePlace1(),
    fakePlace2(),
    fakePlace3(),
    fakePlace4(),
    fakePlace1(),
    fakePlace2(),
    fakePlace3(),
    fakePlace4(),
    fakePlace1(),
    fakePlace2()
)

fun fakePlacePagingDataSource() = createPagingSource<Int, Place> {
    delay(2000)
    PageData.Success(
        (it ?: 0) + 1,
        fakePlaceList + fakePlaceList + fakePlaceList + fakePlaceList + fakePlaceList + fakePlaceList
    )
}