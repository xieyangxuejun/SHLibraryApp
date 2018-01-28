package com.foretree.shlibraryapp.widgets.refresh

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.dyhdyh.widget.swiperefresh.listener.OnRefreshListener2
import com.dyhdyh.widget.swiperefresh.paging.PagingHelper
import com.dyhdyh.widget.swiperefresh.view.SwipeRefreshRecyclerView
import com.foretree.shlibraryapp.Constants
import com.foretree.shlibraryapp.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * Created by silen on 28/01/2018.
 */
class SmartRecyclerView : RelativeLayout, IPagingRefresh {
    private lateinit var view: SwipeRefreshRecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout

    private var mOnRefreshListener: OnRefreshListener2? = null

    private var mStartPage: Int = 0
    var pageCount: Int = 0
        private set

    val recyclerView: RecyclerView
        get() = view.view

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    fun getView(): SwipeRefreshRecyclerView {
        return view
    }

    fun getRefreshLayout(): SmartRefreshLayout {
        return refreshLayout
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun init(context: Context, attrs: AttributeSet?) {
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.SmartRecyclerView)
        mStartPage = a.getInt(R.styleable.SmartRecyclerView_startPage, Constants.START_PAGE)
        pageCount = a.getInt(R.styleable.SmartRecyclerView_pageCount, Constants.DEFAULT_PAGE_COUNT)
        val refreshEnabled = a.getBoolean(R.styleable.SmartRecyclerView_refreshEnabled, true)
        val loadMoreEnabled = a.getBoolean(R.styleable.SmartRecyclerView_loadMoreEnabled, true)

        val recyclerClipToPadding = a.getBoolean(R.styleable.SmartRecyclerView_recyclerClipToPadding, true)
        val recyclerPaddingLeft = a.getDimensionPixelSize(R.styleable.SmartRecyclerView_recyclerPaddingLeft, 0)
        val recyclerPaddingTop = a.getDimensionPixelSize(R.styleable.SmartRecyclerView_recyclerPaddingTop, 0)
        val recyclerPaddingRight = a.getDimensionPixelSize(R.styleable.SmartRecyclerView_recyclerPaddingRight, 0)
        val recyclerPaddingBottom = a.getDimensionPixelSize(R.styleable.SmartRecyclerView_recyclerPaddingBottom, 0)
        a.recycle()

        View.inflate(getContext(), R.layout.layout_smart_refresh_recycler, this)

        //加载更多
        view = findViewById(R.id.rv_swipe)
        view.id = View.generateViewId()
        view.view.clipToPadding = recyclerClipToPadding
        view.view.setPadding(recyclerPaddingLeft, recyclerPaddingTop, recyclerPaddingRight, recyclerPaddingBottom)
        view.isEnabled = false
        view.pagingHelper = PagingHelper(mStartPage, pageCount)
        view.setLoadMoreEnabled(loadMoreEnabled)
        view.setOnLoadMoreListener {
            if (mOnRefreshListener != null) {
                mOnRefreshListener!!.onRefresh(false)
            }
        }

        //刷新
        refreshLayout = findViewById(R.id.layout_refresh)
        refreshLayout.isEnableOverScrollBounce = false
        refreshLayout.isEnableRefresh = refreshEnabled
        refreshLayout.setOnRefreshListener { layout ->
            if (mOnRefreshListener != null) {
                mOnRefreshListener!!.onRefresh(true)
            }
        }
    }


    fun setOnRefreshListener(listener: OnRefreshListener2) {
        this.mOnRefreshListener = listener
    }


    override fun getStartPage(): Int {
        return mStartPage
    }

    override fun getPage(): Int {
        return view.pagingHelper.page
    }

    override fun addPage() {
        view.pagingHelper.pageDown()
    }

    override fun resetPage() {
        view.pagingHelper.resetPage()
    }

}