package com.whoisthat.pokemon.module

import com.whoisthat.pokemon.domain.coroutine.DefaultDispatcherProvider
import com.whoisthat.pokemon.domain.source.DispatcherProvider
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
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}