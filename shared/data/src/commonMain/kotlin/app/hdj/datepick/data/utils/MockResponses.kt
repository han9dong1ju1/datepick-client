package app.hdj.datepick.data.utils

import app.hdj.datepick.data.remote.PagingResponse
import app.hdj.datepick.data.model.response.course.CourseResponse
import app.hdj.datepick.data.model.response.course.CourseTagResponse
import app.hdj.datepick.data.model.response.place.PlaceCategoryResponse
import app.hdj.datepick.data.model.response.place.PlaceResponse
import app.hdj.datepick.data.model.response.user.UserResponse
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.model.user.UserGender
import kotlin.random.Random

object MockResponses {

    fun place() = PlaceResponse(
        id = Random.nextLong(),
        categories = listOf(PlaceCategoryResponse(0, "음식점")),
        kakaoId = Random.nextLong().toString(),
        name = listOf("삼청동수제비", "스시카메", "우블리", "동경화로", "중경삼림").random(),
        address = "서울 종로구 삼청로 101 - 1(우)03049",
        latitude = 37.5844951,
        longitude = 126.9818969,
        rating = 4.3,
        isPicked = false,
        imageUrl =
        if (Random.nextBoolean())
            "https://postfiles.pstatic.net/MjAyMTAyMjNfMjQ5/MDAxNjE0MDQ4NDEwMDYy.JCXTJWhG8eUrNQjRkKtJpB7C3fDc5wyMm66xiG7sCcIg.Dxlm9hUXzUgHSxq4e0bz40d3nxMthzXMffnvyDUCyD0g.JPEG.hyonikim/IMG_1835.jpg?type=w773"
        else null
    )

    fun course(year: String, monthString: String, dayString: String) = CourseResponse(
        id = Random.nextLong(),
        title = listOf("1주년 기념 이벤트", "기념일 데이트", "소소한 데이트", "여자친구 생일!!!").random(),
        meetAt = "${year}-${monthString}-${dayString}T00:00:00Z",
        imageUrl = if (Random.nextBoolean()) "https://picsum.photos/id/${Random.nextInt(1, 1000)}/200/300" else null,
        isPrivate = Random.nextBoolean(),
        viewCount = Random.nextLong(),
        pickCount = Random.nextLong(),
        isPicked = false,
        user = UserResponse(0, "Harry", "https://picsum.photos/200/${Random.nextInt(300, 500)}", UserGender.M),
        tags = listOf(
            CourseTagResponse(1, "크리스마스"),
            CourseTagResponse(2, "여행"),
            CourseTagResponse(3, "기념일"),
            CourseTagResponse(4, "축제"),
            CourseTagResponse(5, "생일"),
        ).shuffled().subList(0, Random.nextInt(1, 4))
    )

    fun coursePaged(page: Long) = PagingResponse(
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