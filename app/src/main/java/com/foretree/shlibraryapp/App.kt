package com.foretree.shlibraryapp

import android.app.Application
import android.content.Context
import com.foretree.shlibraryapp.extensions.DelegatesExt

/**
 * Application
 * Created by silen on 20/01/2018.
 */

class App: Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
        fun getContext(): Context {
            return instance.baseContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
