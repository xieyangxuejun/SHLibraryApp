package com.foretree.shlibraryapp.base.delegate

import android.content.Context
import com.dyhdyh.widget.loading.dialog.LoadingDialog
import com.foretree.shlibraryapp.R

/**
 * Created by silen on 29/01/2018.
 */
class LoadingDialogDelegateImpl(context: Context): LoadingDelegate {
    protected var mContext: Context = context

    override fun show() {
        LoadingDialog.make(mContext).setMessage(getDefaultLoadingMessage()).show()
    }

    override fun cancel() {
        LoadingDialog.cancel()
    }


    override fun showError(message: CharSequence, e: Throwable) {

    }

    fun getDefaultLoadingMessage(): CharSequence {
        return mContext.getString(R.string.message_fail_loading_error)
    }
}