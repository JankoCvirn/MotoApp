package com.cvirn.mototest

import android.app.Application
import com.cvirn.mototest.di.apolloModule
import com.cvirn.mototest.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MotoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MotoApp)
            modules(
                viewModelModule,
                apolloModule,
            )
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
