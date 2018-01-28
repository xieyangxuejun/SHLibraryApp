package com.foretree.shlibraryapp.support.observer

import android.support.v7.widget.RecyclerView
import android.view.View
import com.dyhdyh.widget.swiperefresh.loadmore.LoadMoreFooter
import com.dyhdyh.widget.swiperefresh.loadmore.RecyclerLoadMoreHelper
import com.dyhdyh.widget.swiperefresh.paging.PagingWrapper
import com.foretree.shlibraryapp.Constants
import com.foretree.shlibraryapp.base.delegate.AppLoadingViewDelegateImpl
import com.foretree.shlibraryapp.base.delegate.AppPagingDelegateImpl
import com.foretree.shlibraryapp.base.delegate.LoadingDelegate
import com.foretree.shlibraryapp.ui.adapter.AbstractRecyclerAdapter
import com.foretree.shlibraryapp.widgets.refresh.SmartRecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout

/**
 * Created by silen on 28/01/2018.
 */
abstract class AppPagingObserver<T> : LoadingBarObserver<T> {
    private val mPagingHelper: PagingWrapper
    private val mLoadMoreHelper: RecyclerLoadMoreHelper
    private val mRefreshLayout: RefreshLayout

    private val mLoadingDelegate: LoadingDelegate?

    private val START_PAGE = 0

    constructor(parent: View?, smartRecyclerView: SmartRecyclerView)
            : this(parent, smartRecyclerView, null)

    constructor(parent: View?, smartRecyclerView: SmartRecyclerView, loadingDelegate: LoadingDelegate?)
            : this(parent, smartRecyclerView.getRefreshLayout(), smartRecyclerView.getView().pagingHelper, smartRecyclerView.getView().loadMoreHelper, loadingDelegate)

    constructor(parent: View?, refreshLayout: RefreshLayout, pagingHelper: PagingWrapper, loadMoreHelper: RecyclerLoadMoreHelper)
            : this(parent, refreshLayout, pagingHelper, loadMoreHelper, null)

    constructor(parent: View?, refreshLayout: RefreshLayout, pagingHelper: PagingWrapper, loadMoreHelper: RecyclerLoadMoreHelper, loadingDelegate: LoadingDelegate?) : super(parent!!) {
        this.mPagingHelper = pagingHelper
        this.mRefreshLayout = refreshLayout
        this.mLoadMoreHelper = loadMoreHelper
        this.mLoadingDelegate = loadingDelegate
    }

    fun getLoadMoreHelper(): RecyclerLoadMoreHelper {
        return mLoadMoreHelper
    }

    fun getRefreshLayout(): RefreshLayout {
        return mRefreshLayout
    }

    override fun createDelegate(): LoadingDelegate {
        return object : AppPagingDelegateImpl(getParent(), mRefreshLayout, mLoadMoreHelper, mLoadingDelegate) {

            override fun getEmptyView(): View {
                return this.getEmptyView()
            }

            override fun clickError() {
                onClickError()
            }
        }
    }

    private fun getPageSize(): Int {
        return Constants.DEFAULT_PAGE_COUNT
    }

    open fun getPageCount(response: T): Int {
        return this.getPageSize()
    }

    private fun getStartPage(): Int {
        return START_PAGE
    }

    override fun onNextResponse(t: T) {
        val page = mPagingHelper.page
        val startPage = getStartPage()
        val responseList = getResponseList(t)
        //如果是第一页
        if (page == startPage) {
            onRefresh(t)
            val adapter = mLoadMoreHelper.innerAdapter
            if (adapter != null) {
                val itemCount = adapter.itemCount
                if (itemCount <= 0) {
                    showEmptyDataView()
                }
            }
        } else {
            onLoadMore(t, mLoadMoreHelper.innerAdapter, responseList)
        }

        //第一页个数不足一页的个数
        if (page == startPage && responseList.isEmpty()) {
            mLoadMoreHelper.loadMoreFooter.state = LoadMoreFooter.State.GONE
        } else if (getPageCount(t) > responseList.size) {
            mLoadMoreHelper.loadMoreFooter.state = LoadMoreFooter.State.THE_END
        } else {
            mLoadMoreHelper.loadMoreFooter.state = LoadMoreFooter.State.LOADING
            mPagingHelper.pageDown()
        }
    }


    abstract fun onRefresh(response: T)

    fun onLoadMore(response: T, adapter: RecyclerView.Adapter<*>, responseList: List<Nothing>) {
        val innerAdapter = mLoadMoreHelper.innerAdapter
        if (innerAdapter is AbstractRecyclerAdapter<*, *>) {
            innerAdapter.addItemAll(responseList)
        }
    }

    abstract fun getResponseList(response: T): List<Nothing>

    open fun showEmptyDataView() {
        (getDelegate() as AppLoadingViewDelegateImpl).showEmptyDataError()
    }

}