package com.whoisthat.pokemon.module

import android.content.Context
import androidx.room.Room
import com.whoisthat.pokemon.domain.source.DispatcherProvider
import com.whoisthat.pokemon.local.data.PokemonDatabase
import com.whoisthat.pokemon.local.data.dao.PokemonDao
import com.whoisthat.pokemon.local.data.repository.PokemonDataStore
import com.whoisthat.pokemon.local.data.source.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            PokemonDatabase::class.java,
            PokemonDatabase.NAME
            ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(pokemonDatabase: PokemonDatabase) : PokemonDao
     = pokemonDatabase.pokemonDao()

    @Provides
    @Singleton
    fun provideDataStore(pokemonDao: PokemonDao, dispatcherProvider: DispatcherProvider) : DataStore
     = PokemonDataStore(pokemonDao, dispatcherProvider)
}