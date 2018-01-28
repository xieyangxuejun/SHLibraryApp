package com.foretree.shlibraryapp.base

import com.foretree.shlibraryapp.base.delegate.LoadingDelegate
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by silen on 22/01/2018.
 */
abstract class AbstractLoadingObserver<T> : Observer<T> {
    private val mLoadingDelegate: LoadingDelegate? = null

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        if (isLoading()) getDelegate().show()
    }

    override fun onNext(t: T) {
        try {
            if (isLoading()) getDelegate().cancel()
            onNextResponse(t)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onError(e: Throwable) {
        try {
            if (isLoading()) getDelegate().cancel()
            onResponseError(e)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun getDelegate(): LoadingDelegate = mLoadingDelegate ?: createDelegate()

    protected abstract fun onNextResponse(t: T)
    protected abstract fun createDelegate(): LoadingDelegate

    fun isLoading(): Boolean = true

    fun onResponseError(e: Throwable) {
        val message: CharSequence = (e as? ModelFailureException)?.message ?: getErrorMessage()
        getDelegate().showError(message, e)
    }

    private fun getErrorMessage(): CharSequence = ""
}