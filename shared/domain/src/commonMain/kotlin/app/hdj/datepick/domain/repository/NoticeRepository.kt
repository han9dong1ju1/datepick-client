package app.hdj.datepick.domain.repository

import kotlinx.coroutines.flow.Flow

interface NoticeRepository {

    fun getNotices() :
    fun getNoticesPaged()

}