package app.hdj.datepick.domain.usecase.featured

import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.utils.createDynamicLink
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout

@Singleton
class GetFeaturedDynamicLinkUseCase @Inject constructor() {
    operator fun invoke(input: Featured): Flow<String> = flow {
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