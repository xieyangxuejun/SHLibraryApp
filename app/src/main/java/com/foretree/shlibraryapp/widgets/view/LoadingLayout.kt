package com.foretree.shlibraryapp.widgets.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.foretree.shlibraryapp.R

/**
 * Created by silen on 28/01/2018.
 */
class LoadingLayout: RelativeLayout {

    private var mLoadingView: ImageView? = null
    private var mMessageView: TextView? = null
    private var mMainView: View? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?):this(context, attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        View.inflate(getContext(), R.layout.layout_loading_x, this)
        mMainView = findViewById(R.id.layout_main)
        mLoadingView = findViewById<View>(R.id.iv_loading) as ImageView
        mMessageView = findViewById<View>(R.id.tv_loading_message) as TextView
    }

    fun setMessageText(message: CharSequence) {
        this.mMessageView?.visibility = View.VISIBLE
        this.mMessageView?.text = message
    }

    fun setMessageText(@StringRes messageRes: Int) {
        this.mMessageView?.visibility = View.VISIBLE
        this.mMessageView?.setText(messageRes)
    }


    fun setMessageColor(@ColorRes colorRes: Int) {
        this.mMessageView?.setTextColor(resources.getColor(colorRes))
    }

    override fun setBackgroundResource(@DrawableRes resid: Int) {
        this.mMainView?.setBackgroundResource(resid)
    }

    override fun getBackground(): Drawable? {
        return this.mMainView?.background
    }


    companion object {
        private fun create(context: Context, text: CharSequence?): LoadingLayout {
            val layout = LoadingLayout(context)
            layout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            layout.setMessageText(text!!)
            return layout
        }


        fun createMatch(context: Context, @StringRes stringRes: Int): RelativeLayout {
            return createMatch(context, context.resources.getText(stringRes))
        }

        fun createMatch(context: Context, text: CharSequence?): RelativeLayout {
            val layout = create(context, text)
            val parent = TouchRelativeLayout(context)
            parent.setBackgroundResource(R.color.colorF7)
            val rlp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
            layout.layoutParams = layoutParams
            layout.id = R.id.layout_loading_view
            parent.setLayoutParams(rlp)
            parent.addView(layout)
            return parent
        }
    }
}