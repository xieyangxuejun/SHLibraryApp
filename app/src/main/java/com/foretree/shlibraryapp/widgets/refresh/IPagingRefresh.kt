package com.foretree.shlibraryapp.widgets.refresh;

/**
 * Created by silen on 28/01/2018.
 */

interface IPagingRefresh {
    fun getStartPage(): Int

    fun getPage(): Int

    fun addPage()

    fun resetPage()
}
