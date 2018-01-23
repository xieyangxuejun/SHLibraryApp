package com.foretree.shlibraryapp.support

import com.foretree.shlibraryapp.data.BaseXmlRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 封装请求
 * Created by silen on 21/01/2018.
 */
object NetGo {
    private lateinit var retrofit: Retrofit
    private var requestXml: BaseXmlRequest<Any>? = null

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build()
    }

    fun <T> createByXml(service: Class<T>): T {
        return createByXml(UrlHelper.SERVER_RELEASE, service)
    }

    fun <T> createByXml(baseUrl: String, service: Class<T>): T {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(service)
    }
}