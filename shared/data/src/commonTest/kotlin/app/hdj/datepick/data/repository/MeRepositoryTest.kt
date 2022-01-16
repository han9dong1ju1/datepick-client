@file:Suppress("IllegalIdentifier", "NonAsciiCharacters")

package app.hdj.datepick.data.repository

import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.domain.repository.MeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MeRepositoryTest {

    @MockK
    lateinit var repository: MeRepository

    @BeforeTest
    fun before() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `Me 를 fetch 에 실패했을때 Cache 된 Me 가 잘 노출되어야한다`() = runTest {
        val cacheMe = object : User {
            override val gender: UserGender? = null
            override val id: Long = 0
            override val nickname: String = ""
            override val isMe: Boolean = true
            override val imageUrl: String = ""
        }

        coEvery { repository.cache() } returns cacheMe

        coEvery { repository.fetch() } returns flow {
            emitState(repository.cache()) { throw Exception("Failed") }
        }

        repository.fetch().onEach {
            if (it.isStateFailed()) {
                println("Failed : Cached User Id is ${it.cachedData?.id}")
                assertEquals(it.cachedData, cacheMe)
            } else {
                assertTrue(false)
            }
        }.collect()

    }

}