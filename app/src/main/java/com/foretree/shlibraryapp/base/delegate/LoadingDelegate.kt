package com.foretree.shlibraryapp.base.delegate

/**
 * Created by silen on 22/01/2018.
 */
interface LoadingDelegate {
    fun show()
    fun cancel()
    fun showError(message: CharSequence, e: Throwable)
}