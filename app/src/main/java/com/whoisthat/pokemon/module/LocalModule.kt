package com.whoisthat.pokemon.module

import android.content.Context
import androidx.room.Room
import com.whoisthat.pokemon.local.data.PokemonDatabase
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
}