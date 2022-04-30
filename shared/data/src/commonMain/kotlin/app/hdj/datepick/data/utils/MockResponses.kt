package app.hdj.datepick.data.utils

import app.hdj.datepick.data.remote.PagingResponse
import app.hdj.datepick.data.model.response.course.CourseResponse
import app.hdj.datepick.data.model.response.course.CourseTagResponse
import app.hdj.datepick.data.model.response.featured.FeaturedResponse
import app.hdj.datepick.data.model.response.place.PlaceCategoryResponse
import app.hdj.datepick.data.model.response.place.PlaceResponse
import app.hdj.datepick.data.model.response.user.PublicUserResponse
import app.hdj.datepick.data.model.response.user.UserResponse
import app.hdj.datepick.domain.model.featured.Featured
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
            "https://picsum.photos/${Random.nextInt(200, 500)}/${Random.nextInt(200, 500)}"
        else null
    )

    fun user() =
        UserResponse(
            10007,
            "2022-04-30T16:23:48.231916",
            "2022-04-30T16:23:48.231916",
            "103897723461882838477",
            "GOOGLE",
            "TYLENOL21474836",
            "jungsanmango@gmail.com",
            UserGender.M.toString(),
            isBanned = false,
            isDeleted = false,
            imageUrl = "https://picsum.photos/${Random.nextInt(200, 500)}/${
                Random.nextInt(
                    200,
                    500
                )
            }",
        )

    fun course(year: String, monthString: String, dayString: String) = CourseResponse(
        id = Random.nextLong(),
        title = listOf("1주년 기념 이벤트", "기념일 데이트", "소소한 데이트", "여자친구 생일!!!").random(),
        meetAt = "${year}-${monthString}-${dayString}T00:00:00Z",
        imageUrl = if (Random.nextBoolean()) "https://picsum.photos/${
            Random.nextInt(200, 500)
        }/${Random.nextInt(200, 500)}" else null,
        isPrivate = Random.nextBoolean(),
        viewCount = Random.nextLong(),
        pickCount = Random.nextLong(),
        isPicked = false,
        user = with(user()) {
            PublicUserResponse(
                id = id,
                nickname = nickname,
                gender = gender?.let { UserGender.valueOf(it) },
                imageUrl = imageUrl
            )
        },
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
        count = 50,
        isLastPage = page == 10L,
        content = (1..Random.nextInt(10, 28)).map {

            val year = 2020 + page
            val month = Random.nextInt(1, 12)
            val day = it

            val monthString = if (month >= 10) month.toString() else "0$month"
            val dayString = if (day >= 10) day.toString() else "0$day"

            course(year.toString(), monthString, dayString)

        }.sortedBy { it.meetAt }
    )

    fun featured() = FeaturedResponse(
        id = Random.nextLong(),
        title = "서울 종로구 재밌는 데이트 코스 10선",
        subtitle = "서울 종로구에서 한옥마을을 포함한 정갈한 데이트 코스들을 만나보세요!",
        imageUrl = "https://picsum.photos/${Random.nextInt(200, 500)}/${Random.nextInt(200, 500)}",
        content = """
                    국회의 정기회는 법률이 정하는 바에 의하여 매년 1회 집회되며, 국회의 임시회는 대통령 또는 국회재적의원 4분의 1 이상의 요구에 의하여 집회된다. 모든 국민은 근로의 권리를 가진다. 국가는 사회적·경제적 방법으로 근로자의 고용의 증진과 적정임금의 보장에 노력하여야 하며, 법률이 정하는 바에 의하여 최저임금제를 시행하여야 한다.

                    대통령이 제1항의 기간내에 공포나 재의의 요구를 하지 아니한 때에도 그 법률안은 법률로서 확정된다. 이 헌법에 의한 최초의 대통령의 임기는 이 헌법시행일로부터 개시한다. 감사위원은 원장의 제청으로 대통령이 임명하고, 그 임기는 4년으로 하며, 1차에 한하여 중임할 수 있다. 국회는 의장 1인과 부의장 2인을 선출한다.

                    대통령은 헌법과 법률이 정하는 바에 의하여 국군을 통수한다. 농업생산성의 제고와 농지의 합리적인 이용을 위하거나 불가피한 사정으로 발생하는 농지의 임대차와 위탁경영은 법률이 정하는 바에 의하여 인정된다. 법원은 최고법원인 대법원과 각급법원으로 조직된다. 국무총리는 대통령을 보좌하며, 행정에 관하여 대통령의 명을 받아 행정각부를 통할한다.

                    환경권의 내용과 행사에 관하여는 법률로 정한다. 대법원과 각급법원의 조직은 법률로 정한다. 이 헌법중 공무원의 임기 또는 중임제한에 관한 규정은 이 헌법에 의하여 그 공무원이 최초로 선출 또는 임명된 때로부터 적용한다. 공무원인 근로자는 법률이 정하는 자에 한하여 단결권·단체교섭권 및 단체행동권을 가진다.

                    국회의 정기회는 법률이 정하는 바에 의하여 매년 1회 집회되며, 국회의 임시회는 대통령 또는 국회재적의원 4분의 1 이상의 요구에 의하여 집회된다. 모든 국민은 근로의 권리를 가진다. 국가는 사회적·경제적 방법으로 근로자의 고용의 증진과 적정임금의 보장에 노력하여야 하며, 법률이 정하는 바에 의하여 최저임금제를 시행하여야 한다.

                    대통령이 제1항의 기간내에 공포나 재의의 요구를 하지 아니한 때에도 그 법률안은 법률로서 확정된다. 이 헌법에 의한 최초의 대통령의 임기는 이 헌법시행일로부터 개시한다. 감사위원은 원장의 제청으로 대통령이 임명하고, 그 임기는 4년으로 하며, 1차에 한하여 중임할 수 있다. 국회는 의장 1인과 부의장 2인을 선출한다.

                    대통령은 헌법과 법률이 정하는 바에 의하여 국군을 통수한다. 농업생산성의 제고와 농지의 합리적인 이용을 위하거나 불가피한 사정으로 발생하는 농지의 임대차와 위탁경영은 법률이 정하는 바에 의하여 인정된다. 법원은 최고법원인 대법원과 각급법원으로 조직된다. 국무총리는 대통령을 보좌하며, 행정에 관하여 대통령의 명을 받아 행정각부를 통할한다.

                    환경권의 내용과 행사에 관하여는 법률로 정한다. 대법원과 각급법원의 조직은 법률로 정한다. 이 헌법중 공무원의 임기 또는 중임제한에 관한 규정은 이 헌법에 의하여 그 공무원이 최초로 선출 또는 임명된 때로부터 적용한다. 공무원인 근로자는 법률이 정하는 자에 한하여 단결권·단체교섭권 및 단체행동권을 가진다.

                """.trimIndent(),
        isPinned = true,

        )

    fun featuredPaged(page: Long) = PagingResponse(
        currentPage = page,
        count = 50,
        isLastPage = page == 10L,
        content = (1..5).map { featured() }
    )


    fun placePaged(page: Long) = PagingResponse(
        currentPage = page,
        count = 50,
        isLastPage = page == 10L,
        content = (1..10).map { place() }
    )
}