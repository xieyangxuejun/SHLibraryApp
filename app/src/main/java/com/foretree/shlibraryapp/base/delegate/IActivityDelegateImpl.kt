package com.foretree.shlibraryapp.base.delegate

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.foretree.shlibraryapp.base.delegate.IActivityDelegate

/**
 * Created by silen on 20/01/2018.
 */
class IActivityDelegateImpl(private var appCompatActivity: AppCompatActivity) : IActivityDelegate {
    override fun onCreateBefore(savedInstanceState: Bundle?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
    }

    override fun onSaveInstanceState(outState: Bundle?) {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

    override fun onBackPressed() {
    }

    override fun finish() {
    }

    override fun overridePendingTransition(enterAnim: Int, exitAnim: Int) {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    }

    fun getActivity():AppCompatActivity {
        return appCompatActivity
    }

}