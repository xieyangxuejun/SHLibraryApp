package com.foretree.shlibraryapp.widgets.refresh

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.foretree.shlibraryapp.R
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import kotlinx.android.synthetic.main.item_refresh_head.view.*
import pl.droidsonroids.gif.GifDrawable
import java.io.IOException

/**
 * Created by silen on 28/01/2018.
 */
class AppRefreshHeader : BaseRefreshHeader {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private lateinit var mLoadingView: ImageView
    private var mMessageView: TextView? = null
    private var gifDrawable: GifDrawable? = null

    companion object {

        fun create(context: Context, text: CharSequence): AppRefreshHeader {
            val layout = AppRefreshHeader(context)
            layout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            //        layout.setMessageText(text);
            return layout
        }

    }

    override fun onAfterViews(context: Context) {
        mLoadingView = iv_loading
        mMessageView = tv_loading_message
        try {
            gifDrawable = GifDrawable(context.assets, "loading.gif")
            mLoadingView.setImageDrawable(gifDrawable)
            gifDrawable!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.item_refresh_head
    }

    override fun refreshing() {

    }

    override fun pullDown() {
        if (gifDrawable != null && !gifDrawable!!.isPlaying)
            gifDrawable?.start()
    }

    override fun pushUp() {
        if (gifDrawable != null && gifDrawable!!.isPlaying)
            gifDrawable?.stop()
    }

    override fun onPullingDown(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {
        pullDown()
    }

    override fun onReleasing(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {

    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate//指定为平移，不能null
    }

    override fun setPrimaryColors(vararg colors: Int) {

    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, extendHeight: Int) {

    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {

    }

    override fun onStartAnimator(layout: RefreshLayout, height: Int, extendHeight: Int) {
        gifDrawable?.start()
    }

    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
        gifDrawable?.stop()
        return 0
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {

    }
}