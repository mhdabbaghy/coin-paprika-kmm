package com.mhd.coinpaprika

import di.initKoin
import android.app.Application
import org.koin.android.ext.koin.androidContext

class CoinPaprikaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@CoinPaprikaApp)
        }
    }
}