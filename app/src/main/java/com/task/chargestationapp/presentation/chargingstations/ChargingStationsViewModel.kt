package com.task.chargestationapp.presentation.chargingstations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.chargestationapp.R
import com.task.chargestationapp.domain.model.ChargingStation
import com.task.chargestationapp.domain.usecase.ChargingStationUseCase
import com.task.chargestationapp.util.Constants
import com.task.chargestationapp.util.Resource
import com.task.chargestationapp.util.Resource.*
import com.task.chargestationapp.util.poller.Poller
import com.task.chargestationapp.util.poller.new
import com.task.chargestationapp.util.poller.stopIfPolling
import com.task.chargestationapp.util.poller.strategy.IndefiniteStrategy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChargingStationsViewModel
    @Inject constructor(private val chargingStationUseCase: ChargingStationUseCase,
    private val coroutineScope: CoroutineScope) : ViewModel()
{
    var state by mutableStateOf(ChargingStationState())
    private val poller: Poller<Flow<Resource<List<ChargingStation>>>> by lazy {
        Poller.new(
            coroutineScope = coroutineScope,
            pollInterval = Constants.POLL_INTERVAL,
            pollStrategy = IndefiniteStrategy()
        )
    }

     fun onEvent(event: ChargingStationsEvent) {
        when(event) {
            is ChargingStationsEvent.PollData -> {
                state = state.copy(isLoading = true)
                viewModelScope.launch (Dispatchers.IO){
                    poller.poll {
                        chargingStationUseCase.getChargingStations().apply {
                            this.collectLatest {
                                state = when(it) {
                                    is Success ->
                                        state.copy(chargingStations = it.data, isLoading = false)
                                    is Error -> {
                                        state.copy(isLoading = false, error = R.string.err_unable_to_load)
                                    }
                                    is Loading -> {
                                        state.copy(isLoading = true)
                                    }
                                }
                            }
                        }

                    }
                }
            }
            is ChargingStationsEvent.StopPollingData -> {
                poller.stopIfPolling()
            }
        }
    }

}