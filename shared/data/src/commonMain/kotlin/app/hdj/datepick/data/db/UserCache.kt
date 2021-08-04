package app.hdj.datepick.data.db

import app.hdj.datepick.UserTable
import app.hdj.datepick.UserTableQueries
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class UserCache @Inject constructor(override val query: UserTableQueries) :
    Cache<UserTable, UserTableQueries> {

    override fun getById(id: String): UserTable? {
        return query.getById(id).executeAsOneOrNull()
    }

    override fun save(data: UserTable) {
        query.insert(data)
    }

    override fun delete(id: String) {
        query.deleteById(id)
    }

    fun deleteMe() = query.deleteMe()

    fun getMe(): UserTable? {
        return query.getMe().executeAsOneOrNull()
    }

}