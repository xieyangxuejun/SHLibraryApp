package com.foretree.shlibraryapp.base.delegate

import android.view.View
import com.dyhdyh.widget.loading.bar.LoadingBar
import com.foretree.shlibraryapp.support.loading.AppLoadingFactory

/**
 * Created by silen on 28/01/2018.
 */
open class AppLoadingViewDelegateImpl(parent: View) : LoadingDelegateImpl(parent) {

    override fun show() {
        LoadingBar.make(mParent, AppLoadingFactory.create(getDefaultLoadingMessage())).show()
    }

    override fun showError(message: CharSequence, e: Throwable) {

    }

    open fun getDefaultLoadingMessage(): CharSequence {
        return ""
    }

    open fun clickError() {

    }

    open fun showEmptyDataError() {

    }
}