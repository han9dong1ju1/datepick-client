package app.hdj.datepick.domain.usecase.course

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.CourseTag
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

@Singleton
class GetFeaturedCoursesUseCase @Inject constructor() : UseCase<Long, Flow<LoadState<List<Course>>>> {
    override fun invoke(input: Long): Flow<LoadState<List<Course>>> = flow {
        emit(LoadState.Loading())
        delay(1000)
        emit(
            LoadState.Success(
                (0..10).map {
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
        )
    }
}