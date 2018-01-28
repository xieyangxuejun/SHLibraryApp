package com.foretree.shlibraryapp.base.delegate

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.foretree.shlibraryapp.extensions.ctx
import com.foretree.shlibraryapp.extensions.slideEnter
import com.foretree.shlibraryapp.extensions.slideExit

/**
 * toolbar
 * Created by silen on 20/01/2018.
 */
interface ToolbarDelegate {
    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar(menuId: Int) {
        if (menuId > 0) {
            toolbar.inflateMenu(menuId)
            toolbar.setOnMenuItemClickListener { onMenuItemClick(it) }
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

    /**
     * 如果设置了menu就要实现此方法
     */
    fun onMenuItemClick(menuItem: MenuItem):Boolean {
        return true
    }
}