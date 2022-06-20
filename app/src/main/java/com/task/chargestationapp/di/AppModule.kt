package com.task.chargestationapp.di

import com.task.chargestationapp.data.remote.ChargeMapApi
import com.task.chargestationapp.data.repository.ChargingStationRepositoryImpl
import com.task.chargestationapp.domain.repository.ChargingStationRepository
import com.task.chargestationapp.domain.usecase.ChargingStationUseCase
import com.task.chargestationapp.domain.usecase.GetChargingStations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChargeMapApi() : ChargeMapApi {

        return Retrofit.Builder()
            .baseUrl(ChargeMapApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideChargingStationRepository(api: ChargeMapApi, dispatcher: CoroutineDispatcher) : ChargingStationRepository {
        return ChargingStationRepositoryImpl(api, dispatcher)
    }

    @Provides
    @Singleton
    fun provideChargingStationUseCase(repository: ChargingStationRepository) : ChargingStationUseCase {
        return ChargingStationUseCase(
            getChargingStations = GetChargingStations(repository = repository)
        )
    }

}