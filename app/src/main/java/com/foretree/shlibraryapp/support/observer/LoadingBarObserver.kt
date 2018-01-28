package com.foretree.shlibraryapp.support.observer

import android.view.View
import com.foretree.shlibraryapp.base.AbstractLoadingObserver
import com.foretree.shlibraryapp.base.delegate.AppLoadingViewDelegateImpl
import com.foretree.shlibraryapp.base.delegate.LoadingDelegate

/**
 * Created by silen on 28/01/2018.
 */
abstract class LoadingBarObserver<T>(parent: View) : AbstractLoadingObserver<T>() {
    protected var mParent: View = parent

    override protected fun createDelegate(): LoadingDelegate {
        return AppLoadingViewDelegateImpl(mParent)
    }

    fun getLoadingMessage(): CharSequence {
        return ""
    }

    fun onClickError() {

    }

    fun getParent(): View {
        return mParent
    }
}