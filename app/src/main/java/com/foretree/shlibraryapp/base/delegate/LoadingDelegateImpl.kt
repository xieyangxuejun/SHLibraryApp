package com.foretree.shlibraryapp.base.delegate;

import android.view.View
import com.dyhdyh.widget.loading.bar.LoadingBar
import com.dyhdyh.widget.loading.factory.MaterialFactory

/**
 * Created by silen on 28/01/2018.
 */

open class LoadingDelegateImpl(parent: View) : LoadingDelegate {
    protected var mParent: View = parent

    override fun show() {
        LoadingBar.make(mParent, MaterialFactory()).show()
    }

    override fun cancel() {
        LoadingBar.make(mParent).cancel()
    }

    override fun showError(message: CharSequence, e: Throwable) {

    }
}
