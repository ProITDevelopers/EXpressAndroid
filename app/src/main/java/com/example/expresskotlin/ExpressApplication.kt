package com.example.expresskotlin

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class ExpressApplication : Application() {

    private lateinit var mInstance : ExpressApplication


    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun  getInstance():ExpressApplication {
        return mInstance
    }
}