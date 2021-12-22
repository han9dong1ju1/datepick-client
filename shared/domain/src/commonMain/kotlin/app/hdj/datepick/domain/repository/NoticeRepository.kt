package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.model.notice.Notice
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {

    fun getNotices() : Flow<List<Notice>>
    fun getPagedNotices()

}