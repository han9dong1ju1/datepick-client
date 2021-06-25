package app.hdj.shared.client.data.cache

import app.hdj.shared.client.domain.entity.Course

open class CourseCache : Cache<Course> {
    override suspend fun get(id: String): Course? {
        TODO("Not yet implemented")
    }

    override suspend fun cache(data: Course) {
        TODO("Not yet implemented")
    }
}