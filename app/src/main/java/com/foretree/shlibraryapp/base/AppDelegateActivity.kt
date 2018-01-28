package com.foretree.shlibraryapp.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.foretree.shlibraryapp.base.delegate.IActivityDelegate
import com.foretree.shlibraryapp.base.delegate.IActivityDelegateImpl

/**
 * Activity代理类
 * Created by silen on 20/01/2018.
 */
abstract class AppDelegateActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        get().onCreateBefore(savedInstanceState)
        super.onCreate(savedInstanceState)
        get().onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        get().onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        get().onResume()
    }

    override fun onPause() {
        super.onPause()
        get().onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        get().onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        get().onBackPressed()
    }

    override fun finish() {
        super.finish()
        get().finish()
    }

    override fun overridePendingTransition(enterAnim: Int, exitAnim: Int) {
        super.overridePendingTransition(enterAnim, exitAnim)
        get().overridePendingTransition(enterAnim, exitAnim)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        get().onActivityResult(requestCode, resultCode, data)
    }

    private fun get(): IActivityDelegate {
        val lazyOf = lazyOf(IActivityDelegateImpl(this))
        return lazyOf.value
    }
}