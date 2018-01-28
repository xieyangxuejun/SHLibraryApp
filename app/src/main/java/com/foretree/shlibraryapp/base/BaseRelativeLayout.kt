package com.foretree.shlibraryapp.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.scwang.smartrefresh.layout.api.RefreshHeader

/**
 * 自定义布局基类
 * Created by silen on 28/01/2018.
 */
abstract class BaseRelativeLayout : RelativeLayout, RefreshHeader {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs, defStyleAttr)
        onAfterViews(context)
    }

    abstract fun onAfterViews(context: Context)

    abstract fun getLayoutId(): Int

    fun initView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(getLayoutId(), this, true)
    }
}