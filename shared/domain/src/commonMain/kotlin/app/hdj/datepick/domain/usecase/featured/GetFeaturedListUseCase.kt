package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.repository.FeaturedRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlin.random.Random

@Singleton
class GetFeaturedListUseCase @Inject constructor(
    private val featuredRepository: FeaturedRepository
) : UseCase<Unit, Flow<LoadState<List<Featured>>>> {

    override fun invoke(input: Unit): Flow<LoadState<List<Featured>>> {
//        return featuredRepository.getTopFeaturedList()
        return flow {
            emit(LoadState.Loading())
            delay(2000)
            emit(LoadState.Success(
                (0..10).map {
                    object : Featured {
                        override val id: Long = 1
                        override val title: String = "서울 종로구 재밌는 데이트 코스 10선"
                        override val subtitle: String = "서울 종로구에서 한옥마을을 포함한 정갈한 데이트 코스들을 만나보세요!"
                        override val imageUrl: String =
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Bukchon_Hanok_Village_북촌_한옥마을_October_1_2020_15.jpg/600px-Bukchon_Hanok_Village_북촌_한옥마을_October_1_2020_15.jpg"
                        override val content: String = ""
                        override val isPinned: Boolean = true
                    }
                }
            ))
        }
    }
}