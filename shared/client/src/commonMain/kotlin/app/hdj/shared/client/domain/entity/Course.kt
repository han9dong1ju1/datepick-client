package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Course(
    override val id: String,
    val name: String,
    val photoUrl: String,
    val categories: List<String>
) : Id


fun fakeCourse1() = Course(
    Random.nextInt().toString(),
    "종로 필수 코스",
    "https://picsum.photos/200/300",
    listOf("분위기", "가성비")
)

fun fakeCourse2() = Course(
    Random.nextInt().toString(),
    "종로 3가 필수 코스 입니다.",
    "https://picsum.photos/210/310",
    listOf("가성비", "가성비")
)

fun fakeCourse3() = Course(
    Random.nextInt().toString(),
    "300일 기념데이트",
    "https://picsum.photos/220/320",
    listOf("분위기", "가성비")
)

val fakeCourseList get() = listOf(
    fakeCourse1(),
    fakeCourse2(),
    fakeCourse3()
)