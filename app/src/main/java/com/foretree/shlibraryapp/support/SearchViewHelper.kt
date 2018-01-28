package com.foretree.shlibraryapp.support

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.foretree.shlibraryapp.App
import com.foretree.shlibraryapp.R
import com.scwang.smartrefresh.layout.util.DensityUtil

/**
 * Created by silen on 28/01/2018.
 */
object SearchViewHelper {
    fun initSearchView(searchView: SearchView) {
        val context = searchView.context
        try {
            //自动补全
            val autoCompleteTextView: AutoCompleteTextView = searchView.findViewById(R.id.search_src_text)
            autoCompleteTextView.threshold = 1
            //设置搜索框左边距
            val editFrame: LinearLayout = searchView.findViewById(R.id.search_edit_frame)
            val editP = editFrame.layoutParams as LinearLayout.LayoutParams
            editP.leftMargin = 0
            editP.rightMargin = 0
            val imageView: ImageView = searchView.findViewById(R.id.search_mag_icon)
            imageView.adjustViewBounds = true
            imageView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val lp3 = imageView.layoutParams as LinearLayout.LayoutParams
            lp3.gravity = Gravity.CENTER_VERTICAL
            lp3.leftMargin = (DensityUtil.dp2px(8f) * App.getBaseScale()).toInt()
            lp3.rightMargin = (DensityUtil.dp2px(-2f) * App.getBaseScale()).toInt()

            val view: View = searchView.findViewById(R.id.search_plate)
            view.setBackgroundColor(context.resources.getColor(R.color.colorTransparent))
            val editText: EditText = searchView.findViewById(R.id.search_src_text)
            editText.setBackgroundColor(Color.TRANSPARENT)
            editText.textSize = 11.5f
            editText.setTextColor(context.resources.getColor(R.color.colorText))
            editText.setHintTextColor(context.resources.getColor(R.color.colorHint))
            val fCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
            fCursorDrawableRes.isAccessible = true
            val mCursorDrawableRes = fCursorDrawableRes.getInt(editText)
            val fEditor = TextView::class.java.getDeclaredField("mEditor")
            fEditor.isAccessible = true
            val editor = fEditor.get(editText)
            val clazz = editor.javaClass
            val fCursorDrawable = clazz.getDeclaredField("mCursorDrawable")
            fCursorDrawable.isAccessible = true
            if (mCursorDrawableRes <= 0) return
            val cursorDrawable = ContextCompat.getDrawable(context, mCursorDrawableRes) ?: return
            val tintDrawable = DrawableCompat.wrap(cursorDrawable)
            DrawableCompat.setTintList(tintDrawable, ColorStateList.valueOf(ContextCompat.getColor(context, R.color.bg_search)))
            val drawables = arrayOf(tintDrawable, tintDrawable)
            fCursorDrawable.set(editor, drawables)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}