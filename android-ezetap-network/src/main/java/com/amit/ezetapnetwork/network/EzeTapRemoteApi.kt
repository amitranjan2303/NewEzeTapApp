package com.amit.ezetapnetwork.network

import android.util.Log
import androidx.annotation.WorkerThread
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class EzeTapRemoteApi {

    private val TAG: String? = "EasyTabRemoteApi"
    private val BASE_URL: String? = "https://demo.ezetap.com/mobileapps/android_assignment.json"
    private val MEDIA_TYPE_JSON: MediaType? =
        ("application/json; " + "charset=utf-8").toMediaTypeOrNull()

    private var okHttpClient: OkHttpClient? = null

    constructor() {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.v("OkHttp", message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(interceptor)
        okHttpClient = okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @WorkerThread
    open fun getNetworkResponse(): String? {
        var networkResponseString: String? = null
        Log.d(TAG, "getNetworkResponse: ")
        val httpUrl: HttpUrl? = BASE_URL!!.toHttpUrlOrNull()
        httpUrl?.newBuilder()?.build()
        val request: Request = Request.Builder().url(httpUrl!!).build()
        val response = okHttpClient?.newCall(request)?.execute()

        if (!response!!.isSuccessful) {
            throw Exception("Error receiving Response. HttpCode: " + response.code)
        }
        val body: ResponseBody? = response.body
        return if (body != null) {
            val responseString = body.string()
            Log.d(TAG, "getNetworkResponse : responseString " + responseString)
            networkResponseString = responseString
            return networkResponseString
        } else {
            throw Exception("Error: response code was " + response.code)
        }
    }

}