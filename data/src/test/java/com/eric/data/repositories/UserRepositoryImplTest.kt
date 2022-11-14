package com.eric.data.repositories

import com.eric.data.dto.UserDTO
import com.eric.data.services.UserService
import konveyor.base.randomBuild
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response


class UserRepositoryImplTest {

    private val mockUserService = mock<UserService>()
    private lateinit var sut: UserRepositoryImpl
    private val response: UserDTO = randomBuild<UserDTO>().copy(bookings = listOf(randomBuild()))

    @Before
    fun setup() {
        sut = UserRepositoryImpl(mockUserService)
    }

    @Test
    fun `get user success`() {
        runBlockingTest {
            whenever(mockUserService.getUser()).thenReturn(
                Response.success(response)
            )

            sut.getUser().collect {
                assert(it.isSuccess())
                it.extractData().apply {
                    assert(username == response.username)
                    assert(lastEnergyLevel == response.bookings.first().car.lastEnergyLevel)
                    assert(subMilesLeft == response.bookings.first().subMilesLeft)
                }
            }
        }
    }

    @Test
    fun `get user failure`() {
        runBlockingTest {
            whenever(mockUserService.getUser()).thenReturn(
                Response.error(404, ResponseBody.create(null, "not found"))
            )
            sut.getUser().collect {
                assert(it.isFailure())
            }
        }
    }


}