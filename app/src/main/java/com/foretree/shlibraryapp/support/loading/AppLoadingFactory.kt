package com.foretree.shlibraryapp.support.loading

import android.content.Context
import android.support.annotation.StringRes
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.dyhdyh.widget.loading.factory.LoadingFactory
import com.foretree.shlibraryapp.R
import com.foretree.shlibraryapp.widgets.view.LoadingLayout

/**
 * Created by silen on 28/01/2018.
 */
class AppLoadingFactory(message: CharSequence?) : LoadingFactory {
    private var mMessage: CharSequence? = message
    private var mLoadingLayout: View? = null

    companion object {
        fun create(context: Context, @StringRes textRes: Int): AppLoadingFactory {
            return AppLoadingFactory(context.getText(textRes))
        }

        fun create(message: CharSequence): AppLoadingFactory {
            return AppLoadingFactory(message)
        }

        fun create(): AppLoadingFactory {
            return AppLoadingFactory(null)
        }
    }

    override fun onCreateView(parent: ViewGroup): View {
        mMessage = if (TextUtils.isEmpty(mMessage)) parent.resources.getText(R.string.POPUP_TITLE_LOADING) else mMessage
        val layout = LoadingLayout.createMatch(parent.context, mMessage)
        mLoadingLayout = layout.findViewById(R.id.layout_loading_view)
        return layout
    }
}