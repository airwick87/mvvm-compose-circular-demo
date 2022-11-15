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