package com.foretree.shlibraryapp.base;

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View

/**
 * java dataBinding base 类
 * Created by silen on 18-1-24.
 */

abstract class DataBindingActivity<out T : ViewDataBinding> : BaseCompatActivity() {
    private lateinit var mBinding: T

    override fun setContentView(view: View?) {
        mBinding = DataBindingUtil.bind(view)
        super.setContentView(view)
    }

    fun getBinding(): T {
        return mBinding
    }
}
