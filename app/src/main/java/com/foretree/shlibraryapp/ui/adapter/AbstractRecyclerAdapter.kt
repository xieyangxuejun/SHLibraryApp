package com.foretree.shlibraryapp.ui.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by silen on 28/01/2018.
 */
abstract class AbstractRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(data: MutableList<T>) : RecyclerView.Adapter<VH>() {
    private var mData: MutableList<T> = data
    protected var mOnItemClickListener: OnItemClickListener? = null
    protected var mItemLongClickListener: OnItemLongClickListener? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        var item: T?
        try {
            item = mData[position]
        } catch (e: IndexOutOfBoundsException) {
            item = null
        }

        this.onBindViewHolder(holder, position, item)
        if (holder.itemView != null) {
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(OnItemClickListenerImpl(this, position))
            }
            if (mItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener(OnItemLongClickListenerImpl(this, position))
            }
        }
    }

    abstract fun onBindViewHolder(holder: VH, position: Int, item: T?)

    override fun getItemCount(): Int {
        return mData.size
    }

    /**
     * 获取条目数据
     *
     * @param position 要获取的对象在适配器中的位置
     * @return position位置的对象
     */
    fun getItem(position: Int): T {
        return mData[position]
    }

    /**
     * 添加一个条目
     *
     * @param item 要添加的数据
     */
    fun addItem(item: T) {
        mData.add(item)
        notifyItemInserted(mData.size - 1)
    }

    /**
     * 添加一个条目到position位置
     *
     * @param position 指定的位置
     * @param item     要添加的数据
     */
    fun addItem(position: Int, item: T) {
        mData.add(position, item)
        notifyItemInserted(position)
    }

    /**
     * 添加多个条目
     *
     * @param list 要添加的数据集合
     */
    fun addItemAll(list: List<T>) {
        val oldSize = mData.size
        mData.addAll(list)
        notifyItemRangeInserted(oldSize, list.size)
    }

    /**
     * 添加多个条目到position位置
     *
     * @param position   指定的位置
     * @param list 要添加的数据集合
     */
    fun addItemAll(position: Int, list: List<T>) {
        mData.addAll(position, list)
        notifyItemRangeInserted(position, list.size)
    }

    /**
     * 删除一个条目
     *
     * 为了避免notifyItemRemoved后导致position错乱,所以再调用notifyItemRangeChanged刷新position
     *
     * @param position 要删除的位置
     */
    fun removeItem(position: Int) {
        mData.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mData.size)
    }


    /**
     * 删除多个条目
     *
     * @param list 要删除的数据集合
     */
    fun removeItemAll(list: List<T>) {
        mData.removeAll(list)
        notifyDataSetChanged()
    }

    /**
     * 清空适配器数据
     */
    fun clear() {
        mData.clear()
        //notifyDataSetChanged();
        notifyItemRangeRemoved(0, mData.size)
    }


    fun getData(): List<T> {
        return mData
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        this.mItemLongClickListener = onItemLongClickListener
    }


    /**
     * 条目点击
     */
    interface OnItemClickListener {
        fun onItemClick(adapter: AbstractRecyclerAdapter<*, *>, view: View, position: Int)
    }

    /**
     * 条目长按
     */
    interface OnItemLongClickListener {
        fun onItemLongClick(adapter: AbstractRecyclerAdapter<*, *>, view: View, position: Int): Boolean
    }

    open class OnItemClickListenerImpl(adapter: AbstractRecyclerAdapter<*, *>, private val mPosition: Int) : View.OnClickListener {
        private val mAdapter: AbstractRecyclerAdapter<*, *> = adapter
        override fun onClick(v: View) {
            mAdapter.mOnItemClickListener!!.onItemClick(mAdapter, v, mPosition)
        }
    }

    open class OnItemLongClickListenerImpl(adapter: AbstractRecyclerAdapter<*, *>, private val mPosition: Int) : View.OnLongClickListener {
        private val mAdapter: AbstractRecyclerAdapter<*, *> = adapter
        override fun onLongClick(v: View): Boolean {
            return mAdapter.mItemLongClickListener!!.onItemLongClick(mAdapter, v, mPosition)
        }
    }

}

abstract class RecyclerHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    constructor(parent: ViewGroup?, @LayoutRes layoutId: Int) : this(LayoutInflater.from(parent?.context).inflate(layoutId, parent, false))

    abstract fun onBindViewHolder(position: Int, item: T)
}

