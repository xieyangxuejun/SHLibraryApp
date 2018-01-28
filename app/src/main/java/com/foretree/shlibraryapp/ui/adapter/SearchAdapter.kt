package com.foretree.shlibraryapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foretree.shlibraryapp.R
import com.foretree.shlibraryapp.data.SearchResponse2
import kotlinx.android.synthetic.main.item_main_search.view.*
import java.util.*

/**
 * Created by silen on 28/01/2018.
 */
class SearchAdapter(data: MutableList<SearchResponse2.BookItem>) : AbstractRecyclerAdapter<SearchResponse2.BookItem, SearchViewHolder>(data) {

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int, item: SearchResponse2.BookItem?) {
        holder.itemView.tvBookTitle.text = item?.title
        holder.itemView.tvBookPublishMessage.text = String.format(Locale.getDefault(),
                holder.itemView.context.resources.getString(R.string.label_book_publish_message_s),
                item?.author, item?.publisher, item?.date)
        holder.itemView.tvBookContent.text = item?.content
        holder.itemView.tvBookCallNo.text = item?.callno
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_main_search, parent, false)
        return SearchViewHolder(view)
    }
}

class SearchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)