package com.foretree.shlibraryapp.widgets.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

/**
 * Created by silen on 28/01/2018.
 */
class TouchRelativeLayout:RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?):this(context, attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):super(context, attrs, defStyleAttr) {
        isClickable = true
    }
}