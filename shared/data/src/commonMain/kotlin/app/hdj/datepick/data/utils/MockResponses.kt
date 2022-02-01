package app.hdj.datepick.data.utils

import app.hdj.datepick.data.api.ApiResponse
import app.hdj.datepick.data.api.PagingResponse
import app.hdj.datepick.data.entity.course.CourseData
import app.hdj.datepick.data.entity.course.CourseTagData
import app.hdj.datepick.data.entity.user.UserData
import app.hdj.datepick.domain.model.user.UserGender
import kotlin.random.Random

object MockResponses {

    fun course(year : String, monthString : String, dayString : String) = CourseData(
        id = Random.nextLong(),
        title = listOf("1주년 기념 이벤트", "기념일 데이트", "소소한 데이트", "여자친구 생일!!!").random(),
        meetAt = "${year}-${monthString}-${dayString}T00:00:00Z",
        imageUrl = if (Random.nextBoolean()) "https://picsum.photos/id/${Random.nextInt(1, 1000)}/200/300" else null,
        isPrivate = Random.nextBoolean(),
        viewCount = Random.nextLong(),
        pickCount = Random.nextLong(),
        isPicked = false,
        user = UserData(0, "Harry", "https://picsum.photos/200/${Random.nextInt(300, 500)}", UserGender.M),
        tags = listOf(
            CourseTagData(1, "크리스마스"),
            CourseTagData(2, "여행"),
            CourseTagData(3, "기념일"),
            CourseTagData(4, "축제"),
            CourseTagData(5, "생일"),
        ).shuffled().subList(0, Random.nextInt(1, 4))
    )

    fun coursePaged(page : Long) = PagingResponse(
        currentPage = page,
        count = 1000,
        isLastPage = false,
        content = (1..Random.nextInt(10, 28)).map {

            val year = 2020 + page
            val month = Random.nextInt(1, 12)
            val day = it

            val monthString = if (month >= 10) month.toString() else "0$month"
            val dayString = if (day >= 10) day.toString() else "0$day"

            course(year.toString(), monthString, dayString)

        }.sortedBy { it.meetAt }
    )
}