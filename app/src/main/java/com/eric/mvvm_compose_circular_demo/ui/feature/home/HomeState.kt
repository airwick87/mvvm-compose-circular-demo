package com.eric.mvvm_compose_circular_demo.ui.feature.home

import com.eric.domain.models.UserDomainModel

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class ShowBooking(val userDomainModel: UserDomainModel) : HomeState()
    data class Error(val message: String) : HomeState()
}