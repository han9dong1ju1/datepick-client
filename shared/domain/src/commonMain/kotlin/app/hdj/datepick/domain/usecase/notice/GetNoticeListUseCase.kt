package app.hdj.datepick.domain.usecase.notice

import app.hdj.datepick.domain.model.notice.Notice
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetNoticeListUseCase @Inject constructor(
    
)  {

    operator fun invoke(input: Unit): List<Notice> {
        TODO("Not yet implemented")
    }

}