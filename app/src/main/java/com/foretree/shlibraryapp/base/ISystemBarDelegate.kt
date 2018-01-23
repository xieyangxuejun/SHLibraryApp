package com.foretree.shlibraryapp.base

import android.support.annotation.ColorRes

/**
 * 状态栏
 * Created by silen on 20/01/2018.
 */
interface ISystemBarDelegate {
    fun bindSystemBar()

    @ColorRes fun getStatusBarColor(): Int
    @ColorRes fun getNavigationBarColor():Int
}