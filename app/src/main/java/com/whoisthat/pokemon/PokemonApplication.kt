package com.whoisthat.pokemon

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PokemonApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(object: Timber.DebugTree(){
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "pokemon", message, t)
            }
        })
    }
}