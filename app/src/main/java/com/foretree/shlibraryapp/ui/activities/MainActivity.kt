package com.foretree.shlibraryapp.ui.activities

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import com.foretree.shlibraryapp.R
import com.foretree.shlibraryapp.base.BaseCompatActivity
import com.foretree.shlibraryapp.base.delegate.ToolbarDelegate
import com.foretree.shlibraryapp.data.SearchResponse2
import com.foretree.shlibraryapp.support.MainRequestHelper
import com.foretree.shlibraryapp.support.observer.AppPagingObserver
import com.foretree.shlibraryapp.ui.adapter.AbstractRecyclerAdapter
import com.foretree.shlibraryapp.ui.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.find

class MainActivity : BaseCompatActivity(),
        ToolbarDelegate, AbstractRecyclerAdapter.OnItemClickListener,
        SearchView.OnQueryTextListener {

    private lateinit var mRequest: MainRequestHelper
    private lateinit var mAdapter: SearchAdapter

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun bindSystemBar() {}

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun onAfterViews() {
        mRequest = MainRequestHelper(this)
        rv_list.getRefreshLayout().isEnableRefresh = false
        sv_search.setOnQueryTextListener(this)
        loadSearchList("机器学习")
    }

    private fun loadSearchList(query: String) {
        mRequest.requestSearch(
                query,
                rv_list.getPage(),
                object : AppPagingObserver<SearchResponse2>(contentView, rv_list) {
                    override fun onRefresh(response: SearchResponse2) {
                        bindRecyclerView(response)
                    }

                    override fun getResponseList(response: SearchResponse2): List<Nothing> {
                        return response.body.model.bookItems as List<Nothing>
                    }
                }
        )
    }

    fun bindRecyclerView(response: SearchResponse2) {
        mAdapter = SearchAdapter(response.body.model.bookItems)
        mAdapter.setOnItemClickListener(this)
        rv_list.getView().setAdapter(mAdapter)
        rv_list.getView().setLayoutManager(LinearLayoutManager(this))
    }


    override fun onItemClick(adapter: AbstractRecyclerAdapter<*, *>, view: View, position: Int) {
        if (adapter is SearchAdapter) {
            val item = adapter.getItem(position)
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        if (query != null ) {
            rv_list.resetPage()
            loadSearchList(query)
            return true
        }

//        loadSearchList("android学习")
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
