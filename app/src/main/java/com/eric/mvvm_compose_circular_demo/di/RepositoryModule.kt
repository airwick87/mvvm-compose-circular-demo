package com.eric.mvvm_compose_circular_demo.di

import com.eric.data.repositories.UserRepositoryImpl
import com.eric.data.services.UserService
import com.eric.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideUserRepository(
        userService: UserService,
    ): UserRepository =
        UserRepositoryImpl(userService)

}