package com.task.chargestationapp.presentation.chargingstations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.task.chargestationapp.R
import com.task.chargestationapp.util.Constants
import com.task.chargestationapp.util.NetworkUtil
import com.task.chargestationapp.util.rememberLifecycleEvent


@Composable
@Destination(start = true)
fun ChargingStationsMapScreen(
    navigator: DestinationsNavigator,
    viewModel: ChargingStationsViewModel = hiltViewModel()
) {
    val lifecycleEvent = rememberLifecycleEvent()

    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(Constants.LATITUDE, Constants.LONGITUDE), 12f)
    }
    val state = viewModel.state

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.onEvent(ChargingStationsEvent.PollData)
        } else if (lifecycleEvent == Lifecycle.Event.ON_PAUSE) {
            viewModel.onEvent(ChargingStationsEvent.StopPollingData)
        }
    }

    if(state.error == null) {
        Scaffold(
            scaffoldState = scaffoldState,
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = uiSettings,
                contentDescription = Constants.CONTENT_DESC_MAP
            ){
                state.chargingStations?.forEach { station ->
                    station.address?.latitude?.let { it1 -> station.address.longitude?.let { it2 ->
                        LatLng(it1, it2)
                    } }
                        ?.let { it2 ->
                            Marker(
                                position = it2,
                                title = station.title,
                                snippet = stringResource(R.string.click_to_view),
                                onInfoWindowClick = {
                                },
                                onClick = {
                                   // it.showInfoWindow()
                                    viewModel.onEvent(ChargingStationsEvent.OnMapMarkerClick(station = station, navigator = navigator))
                                    true
                                },
                                icon = BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_GREEN
                                )
                            )
                        }
                }
            }
        }

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = stringResource(R.string.updating))
                CircularProgressIndicator()
            }
        } else if(state.error != null) {
            var errorMsg = stringResource(state.error)
            if(! NetworkUtil.isInternetAvailable(LocalContext.current)) {
                errorMsg = stringResource(R.string.err_connectivity)
            }
            Text(text = errorMsg, color = MaterialTheme.colors.error)
        }
    }
}