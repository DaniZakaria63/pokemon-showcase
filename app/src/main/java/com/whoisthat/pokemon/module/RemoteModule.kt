package com.whoisthat.pokemon.module

import android.accounts.NetworkErrorException
import android.content.Context
import com.google.gson.GsonBuilder
import com.whoisthat.pokemon.R
import com.whoisthat.pokemon.remote.data.repository.DefaultNetworkService
import com.whoisthat.pokemon.remote.data.source.NetworkEndpoint
import com.whoisthat.pokemon.remote.data.source.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.Properties
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun provideApiKey(
        @ApplicationContext context: Context
    ): String {
        val properties = Properties()
        return try {
            val rawResource = context.resources.openRawResource(R.raw.api)
            properties.load(rawResource)
            return properties.getProperty("key")
        } catch (e: Exception) {
            Timber.e(e)
            "-"
        }
    }

    @Provides
    fun provideHeaderInterceptor(apiKey: String): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            return@Interceptor try {
                val request = chain.request().newBuilder().header("X-Api-Key", apiKey).build()
                chain.proceed(request)
            } catch (e: Exception) {
                Timber.e(e)
                Response.Builder()
                    .code(600)
                    .protocol(Protocol.HTTP_2)
                    .message("No Internet Server Request")
                    .request(chain.request()).build()
            }
        }
    }

    @Provides
    @Singleton
    fun provideNetworkEndpoint(
        headerInterceptor: Interceptor
    ): NetworkEndpoint {
        val logging =
            HttpLoggingInterceptor { Timber.i("API: $it") }.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return Retrofit.Builder()
            .baseUrl(NetworkEndpoint.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(logging)
                    .build()
            )
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )
            .build()
            .create(NetworkEndpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkService(networkEndpoint: NetworkEndpoint): NetworkService {
        return DefaultNetworkService(networkEndpoint)
    }
}