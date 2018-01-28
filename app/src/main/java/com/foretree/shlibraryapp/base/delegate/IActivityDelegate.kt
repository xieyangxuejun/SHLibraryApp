package com.foretree.shlibraryapp.base.delegate

import android.content.Intent
import android.os.Bundle

/**
 * Created by silen on 20/01/2018.
 */

interface IActivityDelegate {
    fun onCreateBefore(savedInstanceState: Bundle?)

    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle?)

    fun onResume()

    fun onPause()

    fun onDestroy()

    fun onBackPressed()

    fun finish()

    fun overridePendingTransition(enterAnim: Int, exitAnim: Int)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
}