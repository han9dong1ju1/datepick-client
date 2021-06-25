package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.api.CourseApi
import app.hdj.shared.client.data.cache.CourseCache
import app.hdj.shared.client.data.repo.CourseRepositoryImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerCourseRepositoryImp @Inject constructor(
    courseCache: CourseCache,
    courseApi: CourseApi,
) : CourseRepositoryImp(courseCache, courseApi)