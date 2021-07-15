package app.hdj.shared.client.domain.entity

import kotlin.random.Random

data class NoticeEvent(
    override val id: String,
    val photoUrl: String,
    val title: String,
    val description: String
) : Id

fun fakeNoticeEvent() = NoticeEvent(
    Random.nextInt().toString(),
    "https://picsum.photos/${Random.nextInt(300, 400)}/${Random.nextInt(200, 300)}/",
    "첫 출시 기념 할인 이벤트!",
    "첫 출시 기념으로 자동 코스 생성 30% 할인을 진행합니다."
)

val fakeNoticeEvents = listOf(
    fakeNoticeEvent(),
    fakeNoticeEvent(),
    fakeNoticeEvent(),
    fakeNoticeEvent(),
    fakeNoticeEvent(),
    fakeNoticeEvent(),
)