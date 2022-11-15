package com.eric.mvvm_compose_circular_demo.ui.feature.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eric.domain.models.getEnergyFloat
import com.eric.domain.models.getMilesFloat
import com.eric.mvvm_compose_circular_demo.R
import com.eric.mvvm_compose_circular_demo.ui.components.CircularProgressText
import com.eric.mvvm_compose_circular_demo.ui.theme.MvvmcomposecirculardemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmcomposecirculardemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val state = viewModel.userState.collectAsState()
                    HomeScreen(state.value) {
                        viewModel.loadUser()
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    state: HomeState,
    onRefreshButtonClicked: () -> Unit
) {
    var progressEnergy by remember { mutableStateOf(0.1f) }
    var progressRemainingMiles by remember { mutableStateOf(0.1f) }
    val energyFloatAnim = animateFloatAsState(
        targetValue = progressEnergy,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value
    val remainingMilesAnim = animateFloatAsState(
        targetValue = progressRemainingMiles,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(modifier = Modifier.padding(12.dp)) {
        Row {
            Button(onClick = {
                onRefreshButtonClicked.invoke()
            }) {
                Text(stringResource(id = R.string.refresh))
            }
        }

        when (state) {
            is HomeState.Error -> Toast.makeText(
                LocalContext.current,
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            HomeState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is HomeState.ShowBooking -> {
                progressEnergy = state.userDomainModel.getEnergyFloat()
                progressRemainingMiles = state.userDomainModel.getMilesFloat()

                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CircularProgressText(
                        modifier = Modifier.weight(1f),
                        progress = remainingMilesAnim,
                        text = stringResource(
                            id = R.string.milesRemaining,
                            state.userDomainModel.subMilesLeft
                        )
                    )
                    CircularProgressText(
                        modifier = Modifier.weight(1f),
                        progress = energyFloatAnim,
                        text = stringResource(
                            id = R.string.energyRemaining,
                            state.userDomainModel.lastEnergyLevel
                        )
                    )
                }
            }
            else -> {}
        }
    }

}

