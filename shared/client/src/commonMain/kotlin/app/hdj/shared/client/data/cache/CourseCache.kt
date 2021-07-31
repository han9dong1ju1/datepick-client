package app.hdj.shared.client.data.cache

import app.hdj.shared.client.domain.entity.CourseMetadata

open class CourseCache : Cache<CourseMetadata> {
    override suspend fun get(id: String): CourseMetadata? {
        TODO("Not yet implemented")
    }

    override suspend fun cache(data: CourseMetadata) {
        TODO("Not yet implemented")
    }
}