package app.hdj.datepick.domain.usecase.notice

import app.hdj.datepick.domain.model.notice.Notice
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class GetNoticeListUseCase @Inject constructor(
    
) : UseCase<Unit, List<Notice>> {

    override fun invoke(input: Unit): List<Notice> {
        TODO("Not yet implemented")
    }

}