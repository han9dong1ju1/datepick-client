package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

@Singleton
class GetFeaturedDetailUseCase @Inject constructor(
    private val featuredRepository: FeaturedRepository
) : UseCase<Long, Flow<LoadState<Featured>>> {

    override operator fun invoke(input: Long): Flow<LoadState<Featured>>
//    = featuredRepository.getById(input)
            = flow {
        emit(LoadState.Loading())
        delay(3000)
        emit(
            LoadState.Success(
                Featured(
                    id = 1,
                    title = "서울 종로구 재밌는 데이트 코스 10선",
                    subtitle = "서울 종로구에서 한옥마을을 포함한 정갈한 데이트 코스들을 만나보세요!",
                    imageUrl =
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Bukchon_Hanok_Village_북촌_한옥마을_October_1_2020_15.jpg/600px-Bukchon_Hanok_Village_북촌_한옥마을_October_1_2020_15.jpg",
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
            )
        )
    }

}