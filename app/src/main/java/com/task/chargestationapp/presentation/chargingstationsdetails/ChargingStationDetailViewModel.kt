package com.task.chargestationapp.presentation.chargingstationsdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.chargestationapp.R
import com.task.chargestationapp.domain.model.ChargingStation
import com.task.chargestationapp.util.Constants.SELECTED_STATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChargingStationDetailViewModel
    @Inject constructor(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var state by mutableStateOf(ChargingStationDetailsState())

    init {
        state = state.copy(isLoading = true)

        viewModelScope.launch {
            val station = savedStateHandle.get<ChargingStation>(SELECTED_STATION)
            state = if(station == null)
                state.copy(chargingStation = station, isLoading = false, error = R.string.err_unable_to_load)
            else
                state.copy(chargingStation = station, isLoading = false)

        }
    }
}

