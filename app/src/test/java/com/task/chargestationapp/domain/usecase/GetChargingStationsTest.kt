package com.task.chargestationapp.domain.usecase

import app.cash.turbine.test
import com.task.chargestationapp.data.repository.FakeChargingStationRepository
import com.task.chargestationapp.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetChargingStationsTest {

    private lateinit var fakeRepository: FakeChargingStationRepository

    @Before
    fun setUp() {
        fakeRepository = FakeChargingStationRepository()
    }

    @Test
    fun `get chargingStations and check success`() = runBlocking {

        GetChargingStations(fakeRepository)().test {
            val progress = awaitItem()
            assert(progress is Resource.Loading)
            delay(200L)
            val status = awaitItem()
            assert(status is Resource.Success)
            val chargingStations = status.data
            assert(chargingStations?.get(0)?.title.equals( "TestTitle1"))
            awaitComplete()
        }
    }
}