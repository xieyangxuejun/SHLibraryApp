package com.foretree.shlibraryapp.base.delegate

import android.view.View
import com.dyhdyh.widget.loading.bar.LoadingBar
import com.dyhdyh.widget.swiperefresh.loadmore.LoadMoreFooter
import com.dyhdyh.widget.swiperefresh.loadmore.RecyclerLoadMoreHelper
import com.scwang.smartrefresh.layout.api.RefreshLayout

/**
 * Created by silen on 29/01/2018.
 */
abstract class AppPagingDelegateImpl : LoadingDelegate {
    private val mLoadingViewDelegate: LoadingDelegate?

    private val mLoadingDialogDelegate: LoadingDelegate

    private val mParent: View

    private val mRefreshLayout: RefreshLayout?
    private val mLoadMoreHelper: RecyclerLoadMoreHelper

    private var mTmpIsRefreshing: Boolean = false
    private var mTmpIsLoadMore: Boolean = false

    constructor(parent: View, refreshLayout: RefreshLayout, loadMoreHelper: RecyclerLoadMoreHelper) : this(parent, refreshLayout, loadMoreHelper, null)
    constructor(parent: View, refreshLayout: RefreshLayout, loadMoreHelper: RecyclerLoadMoreHelper, loadingDelegate: LoadingDelegate?) {
        this.mParent = parent
        this.mLoadingViewDelegate = loadingDelegate ?: object : AppLoadingViewDelegateImpl(mParent) {
            override fun clickError() {
                this.clickError()
            }
        }
        this.mLoadingDialogDelegate = LoadingDialogDelegateImpl(mParent.context)
        this.mRefreshLayout = refreshLayout
        this.mLoadMoreHelper = loadMoreHelper
    }

    override fun showError(message: CharSequence, e: Throwable) {
        //刷新
        if (mTmpIsRefreshing) {
            mTmpIsRefreshing = false
            mLoadingDialogDelegate.showError(message, e)
        } else {
            //加载更多
            val footer = mLoadMoreHelper.loadMoreFooter
            if (footer != null && mTmpIsLoadMore) {
                mTmpIsLoadMore = false
                footer.state = LoadMoreFooter.State.ERROR
            } else {
                mLoadingViewDelegate?.showError(message, e)
            }
        }
    }


    /**
     * 空数据时的显示
     */
    open fun showEmptyDataError() {
        LoadingBar.make(mParent, getEmptyView()).show()
    }

    abstract fun getEmptyView(): View

    override fun show() {
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing || mLoadMoreHelper.isLoadMore) {

        } else {
            mLoadingViewDelegate?.show()
        }
    }

    override fun cancel() {
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing) {
            mTmpIsRefreshing = true
            mRefreshLayout.finishRefresh(500)
        }
        if (mLoadMoreHelper.isLoadMore) {
            mTmpIsLoadMore = true
            mLoadMoreHelper.isLoadMore = false
        } else {
            mLoadingViewDelegate?.cancel()
        }
    }

    open fun clickError() {

    }
}