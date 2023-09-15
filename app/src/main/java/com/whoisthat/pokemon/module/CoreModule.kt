package com.whoisthat.pokemon.module

import com.whoisthat.pokemon.core.data.repository.DefaultPokemonRepository
import com.whoisthat.pokemon.core.data.source.PokemonRepository
import com.whoisthat.pokemon.domain.coroutine.DefaultDispatcherProvider
import com.whoisthat.pokemon.domain.source.DispatcherProvider
import com.whoisthat.pokemon.local.data.source.DataStore
import com.whoisthat.pokemon.remote.data.source.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun providePokemonRepository(
        dataStore: DataStore,
        networkService: NetworkService,
        dispatcherProvider: DispatcherProvider
    ): PokemonRepository = DefaultPokemonRepository(dataStore, networkService, dispatcherProvider)

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}