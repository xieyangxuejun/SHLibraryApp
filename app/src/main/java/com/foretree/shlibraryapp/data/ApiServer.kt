package com.foretree.shlibraryapp.data

import com.foretree.shlibraryapp.support.UrlHelper
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * retrofit接口
 * Created by silen on 21/01/2018.
 */
interface ApiServer {
    @Headers(value = "Content-Type: text/xml; charset=utf-8")
    @POST(UrlHelper.API_SEARCH)
    fun searchBooks(@Body searchRequest: SearchRequest) : Observable<SearchResponse2>

    @Headers(value = "Content-Type: text/xml; charset=utf-8")
    @POST(UrlHelper.API_BOOK_STATUS)
    fun postBookInfo(@Body searchRequest: SearchRequest) : Observable<SearchResponse2>
}