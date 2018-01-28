package com.foretree.shlibraryapp.support

import android.content.Context
import com.foretree.shlibraryapp.Constants
import com.foretree.shlibraryapp.data.ApiServer
import com.foretree.shlibraryapp.data.SearchRequest
import com.foretree.shlibraryapp.data.SearchResponse2
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by silen on 28/01/2018.
 */
class MainRequestHelper(context: Context) {
    private val mContext = context

    fun requestSearch(queryKey: String, page:Int, observer: Observer<SearchResponse2>) {
        val requestModel = SearchRequest.RequestModel(
                "title",
                queryKey,
                Constants.DEFAULT_PAGE_COUNT,
                page
        )
        val requestXml = SearchRequest()
        requestXml.setBody(SearchRequest.RequestBody(requestModel))
        val serverApi: ApiServer = NetGo.createByXml(ApiServer::class.java)
        serverApi.searchBooks(requestXml)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }
}