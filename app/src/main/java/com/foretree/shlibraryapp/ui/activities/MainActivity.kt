package com.foretree.shlibraryapp.ui.activities

import android.support.v7.widget.Toolbar
import android.util.Log
import com.foretree.shlibraryapp.R
import com.foretree.shlibraryapp.base.BaseCompatActivity
import com.foretree.shlibraryapp.data.ApiServer
import com.foretree.shlibraryapp.data.SearchRequest
import com.foretree.shlibraryapp.data.SearchResponse2
import com.foretree.shlibraryapp.support.NetGo
import com.foretree.shlibraryapp.ui.ToolbarDelegate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find

class MainActivity : BaseCompatActivity(), ToolbarDelegate {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun bindSystemBar() {}

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun onAfterViews() {
        val requestModel = SearchRequest.RequestModel(
                "title",
                "机器学习",
                "10",
                "0"
        )
        val requestXml = SearchRequest()
        requestXml.setBody(SearchRequest.RequestBody(requestModel))
        val serverApi: ApiServer = NetGo.createByXml(ApiServer::class.java)
        serverApi.searchBooks(requestXml)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: SearchResponse2? ->
                    Log.d("xy==", "" + t.toString())
                })
    }
}
