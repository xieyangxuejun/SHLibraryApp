package com.foretree.shlibraryapp

import android.app.ActivityManager
import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import android.view.WindowManager
import android.widget.RelativeLayout
import com.dyhdyh.widget.loading.LoadingConfig
import com.dyhdyh.widget.loading.factory.MaterialDialogFactory
import com.foretree.shlibraryapp.extensions.DelegatesExt
import com.foretree.shlibraryapp.support.loading.AppLoadingFactory
import com.foretree.shlibraryapp.widgets.refresh.AppRefreshHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout

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

        fun getBaseScale(): Float {
            return getContext().resources.displayMetrics.widthPixels / (getContext().resources.displayMetrics.density * 360)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (shouldInit()) {
            initLoading()
        }

    }

    private fun shouldInit(): Boolean {
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfo = am.runningAppProcesses
        val mainProcessName = packageName
        val myPid = android.os.Process.myPid()
        return processInfo.any { it.pid == myPid && mainProcessName == it.processName }
    }


    private fun initLoading() {
        LoadingConfig.setFactory(AppLoadingFactory.create(), object : MaterialDialogFactory() {
            override fun onCreateDialog(context: Context): ProgressDialog {
                val dialog = super.onCreateDialog(context)
                dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                return dialog
            }
        })

        SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, _ ->
            val header = AppRefreshHeader.create(context, "")
            header.gravity = RelativeLayout.CENTER_HORIZONTAL
            header
        }
    }
}

