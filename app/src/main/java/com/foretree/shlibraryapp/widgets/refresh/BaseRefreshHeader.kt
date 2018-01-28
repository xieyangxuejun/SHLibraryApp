package com.foretree.shlibraryapp.widgets.refresh

import android.content.Context
import android.util.AttributeSet
import com.foretree.shlibraryapp.base.BaseRelativeLayout

/**
 * Created by silen on 28/01/2018.
 */
abstract class BaseRefreshHeader : BaseRelativeLayout {

    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet?):super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):super(context, attrs, defStyleAttr)

    abstract fun refreshing()
    abstract fun pullDown()
    abstract fun pushUp()
}