package app.hdj.datepick.android.ui.providers.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.CourseTag
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import kotlin.random.Random

class FakeCoursePreviewProvider : PreviewParameterProvider<List<Course>> {
    override val values: Sequence<List<Course>>
        get() = sequenceOf(
            (0..5).map {
                Course(
                    id = Random.nextLong(),
                    title = listOf("1주년 기념 이벤트", "기념일 데이트", "소소한 데이트", "여자친구 생일!!!").random(),
                    meetAt = "${2020 + it}-${Random.nextInt(10, 12)}-${Random.nextInt(10, 30)}T00:00:00Z",
                    imageUrl = "https://picsum.photos/200/${Random.nextInt(300, 500)}",
                    isPrivate = Random.nextBoolean(),
                    viewCount = Random.nextLong(),
                    pickCount = Random.nextLong(),
                    isPicked = false,
                    user = User(
                        id = 0,
                        nickname = "Harry",
                        gender = UserGender.M,
                        imageUrl = "https://picsum.photos/200/${Random.nextInt(300, 500)}"
                    ),
                    tags = listOf(
                        1 to "크리스마스",
                        2 to "여행",
                        3 to "기념일",
                        4 to "축제",
                        5 to "생일"
                    ).shuffled().subList(0, Random.nextInt(1, 4)).map {
                        CourseTag(it.first.toLong(), it.second)
                    }
                )
            }
        )
}