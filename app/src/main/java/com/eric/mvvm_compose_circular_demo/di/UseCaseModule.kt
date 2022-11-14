package com.eric.mvvm_compose_circular_demo.di

import com.eric.domain.repositories.UserRepository
import com.eric.domain.usecases.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideProductListUseCase(
        userRepository: UserRepository,
    ) = GetUserUseCase(userRepository)

}