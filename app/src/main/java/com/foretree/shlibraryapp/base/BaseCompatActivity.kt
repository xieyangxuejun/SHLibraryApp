package com.foretree.shlibraryapp.base

import android.content.Intent
import android.os.Bundle
import com.foretree.shlibraryapp.base.delegate.ISystemBarDelegate
import com.foretree.shlibraryapp.base.system.NavigationBarCompat
import com.foretree.shlibraryapp.base.system.StatusBarCompat

/**
 * base基类
 * Created by silen on 20/01/2018.
 */
@Suppress("DEPRECATION")
abstract class BaseCompatActivity : AppDelegateActivity(), ISystemBarDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.bindSystemBar()
        super.onCreate(savedInstanceState)
        this.initIntent(intent)
        setContentView(getContentViewId())
        this.initViews()
        this.onAfterViews()
    }

    protected fun initViews() {

    }

    protected fun initIntent(intent: Intent?) {

    }

    override fun bindSystemBar() {
        val statusBarColor = getStatusBarColor()
        if (statusBarColor > 0) {
            StatusBarCompat.setStatusBarColor(this, resources.getColor(statusBarColor))
        }
        val navigationBarColor = getNavigationBarColor()
        if (navigationBarColor > 0) {
            NavigationBarCompat.setNavigationBarColor(window, resources.getColor(navigationBarColor))
        }
    }

    override fun getStatusBarColor(): Int {
        return android.R.color.transparent
    }

    override fun getNavigationBarColor(): Int {
        return android.R.color.black
    }

    abstract fun getContentViewId(): Int

    abstract fun onAfterViews()
}