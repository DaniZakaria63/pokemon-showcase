package com.whoisthat.pokemon.module

import com.google.gson.GsonBuilder
import com.whoisthat.pokemon.remote.data.repository.DefaultNetworkService
import com.whoisthat.pokemon.remote.data.source.NetworkEndpoint
import com.whoisthat.pokemon.remote.data.source.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideNetworkEndpoint(): NetworkEndpoint{
        return Retrofit.Builder()
            .baseUrl(NetworkEndpoint.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )
            .build()
            .create(NetworkEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkService(networkEndpoint: NetworkEndpoint): NetworkService{
        return DefaultNetworkService(networkEndpoint)
    }
}