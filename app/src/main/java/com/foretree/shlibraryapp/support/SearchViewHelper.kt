package com.foretree.shlibraryapp.support

import android.content.Context
import android.widget.SearchView

/**
 * Created by silen on 28/01/2018.
 */
object SearchViewHelper {
    private fun initSearchView(context: Context, searchView: SearchView) {
//        //自动补全
//        val autoCompleteTextView = searchView.findViewById(R.id.search_src_text)
//        autoCompleteTextView.setThreshold(1)
//        //设置搜索框左边距
//        val editFrame = searchView.findViewById(R.id.search_edit_frame) as LinearLayout
//        val editP = editFrame.layoutParams as LinearLayout.LayoutParams
//        editP.leftMargin = 0
//        editP.rightMargin = 0
//        val imageView = findViewById(R.id.search_mag_icon) as ImageView
//        imageView.adjustViewBounds = true
//        imageView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        val lp3 = imageView.layoutParams as LayoutParams
//        lp3.gravity = Gravity.CENTER_VERTICAL
//        lp3.leftMargin = DensityUtil.dip2px(8f) * App.getBaseScale()
//        lp3.rightMargin = DensityUtil.dip2px(-2f) * App.getBaseScale()
//
//        val view = searchView.findViewById(R.id.search_plate)
//        view.setBackgroundColor(getResources().getColor(R.color.colorTransparent))
//        val editText = searchView.findViewById(R.id.search_src_text) as EditText
//        GrowthingIOHelper.trackEdit(editText)
//        editText.setBackgroundColor(Color.TRANSPARENT)
//        editText.textSize = 11.5f
//        editText.setTextColor(getResources().getColor(R.color.colorText))
//        editText.setHintTextColor(getResources().getColor(R.color.colorHint))
//        try {
//            val fCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
//            fCursorDrawableRes.isAccessible = true
//            val mCursorDrawableRes = fCursorDrawableRes.getInt(editText)
//            val fEditor = TextView::class.java.getDeclaredField("mEditor")
//            fEditor.isAccessible = true
//            val editor = fEditor.get(editText)
//            val clazz = editor.javaClass
//            val fCursorDrawable = clazz.getDeclaredField("mCursorDrawable")
//            fCursorDrawable.isAccessible = true
//            if (mCursorDrawableRes <= 0) return
//            val cursorDrawable = ContextCompat.getDrawable(searchView.getContext(), mCursorDrawableRes) ?: return
//            val tintDrawable = DrawableCompat.wrap(cursorDrawable)
//            DrawableCompat.setTintList(tintDrawable, ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.bg_search)))
//            val drawables = arrayOf(tintDrawable, tintDrawable)
//            fCursorDrawable.set(editor, drawables)
//        } catch (t: Throwable) {
//            t.printStackTrace()
//        }
    }
}