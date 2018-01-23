package com.foretree.shlibraryapp.data

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by silen on 21/01/2018.
 */
class RequestXmlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val response: Response? = null
        if (chain != null) {
            val request = chain.request()
            return chain.proceed(request)
        }
        return response!!
    }

}