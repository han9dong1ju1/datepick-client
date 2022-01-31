package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import app.hdj.datepick.utils.createDynamicLink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout

@Singleton
class GetFeaturedDynamicLinkUseCase @Inject constructor() : UseCase<Featured, Flow<String>> {
    override fun invoke(input: Featured): Flow<String> = flow {
        val link = createDynamicLink(
            "featured/${input.id}",
            input.title,
            input.subtitle,
            input.imageUrl,
            1_00_00,
            "1.00.00"
        )
        withTimeout(5000) {
            link?.let {
                emit(it)
            }
        }
    }
}