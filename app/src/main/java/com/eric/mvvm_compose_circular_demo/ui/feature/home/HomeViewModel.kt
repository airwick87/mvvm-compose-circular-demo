package com.eric.mvvm_compose_circular_demo.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eric.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val mutableUserState = MutableStateFlow<HomeState>(HomeState.Idle)
    val userState: StateFlow<HomeState> get() = mutableUserState

    fun loadUser() {
        viewModelScope.launch {
            mutableUserState.value = HomeState.Loading
            getUserUseCase.getUserDetails().collect {
                mutableUserState.value = when {
                    it.isSuccess() -> {
                        HomeState.ShowBooking(it.extractData())
                    }
                    it.isFailure() -> {
                        HomeState.Error(it.extractError().message)
                    }
                    else -> {
                        HomeState.Idle
                    }
                }
            }
        }

    }

}