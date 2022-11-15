package com.eric.mvvm_compose_circular_demo.ui.feature.home

import com.eric.domain.models.UserDomainModel
import com.eric.domain.shared.ErrorEntity
import com.eric.domain.shared.ResultResponse
import com.eric.domain.usecases.GetUserUseCase
import konveyor.base.randomBuild
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val mockGetUserUseCase = mock<GetUserUseCase>()
    private val userDomainModel: UserDomainModel = randomBuild()
    private lateinit var sut: HomeViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        sut = HomeViewModel(mockGetUserUseCase)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load user success`() {
        runTest {
            whenever(mockGetUserUseCase.getUserDetails()).thenReturn(
                flowOf(
                    ResultResponse.Success(userDomainModel)
                )
            )

            sut.loadUser()
            val result = sut.userState.value as HomeState.ShowBooking
            assert(result.userDomainModel.username == userDomainModel.username)
            assert(result.userDomainModel.subMilesLeft == userDomainModel.subMilesLeft)
            assert(result.userDomainModel.lastEnergyLevel == userDomainModel.lastEnergyLevel)
        }
    }

    @Test
    fun `load user fail`() {
        runTest {
            whenever(mockGetUserUseCase.getUserDetails()).thenReturn(
                flowOf(
                    ResultResponse.Failure(ErrorEntity.Generic())
                )
            )

            sut.loadUser()
            assert(sut.userState.value is HomeState.Error)
        }
    }

}