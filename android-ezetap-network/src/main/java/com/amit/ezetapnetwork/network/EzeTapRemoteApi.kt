package com.amit.ezetapnetwork.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.WorkerThread
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.InputStream
import java.net.URL
import java.util.concurrent.TimeUnit

const val TAG: String = "EasyTabRemoteApi"

class EzeTapRemoteApi {
    private var okHttpClient: OkHttpClient? = null

    init {
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
    fun fetchCustomUI(url: String?): String {
        val networkResponseString: String
        Log.d(TAG, "getNetworkResponse: ")
        val httpUrl: HttpUrl? = url?.toHttpUrlOrNull()
        httpUrl?.newBuilder()?.build()
        val request: Request = Request.Builder().url(httpUrl!!).build()
        val response = okHttpClient?.newCall(request)?.execute()

        if (response?.isSuccessful?.not() == true) {
            throw Exception("Error receiving Response. HttpCode: ${response.code}")
        }
        val body: ResponseBody? = response?.body
        return if (body != null) {
            val responseString = body.string()
            Log.d(TAG, "getNetworkResponse : responseString $responseString")
            networkResponseString = responseString
            networkResponseString
        } else {
            throw Exception("Error: response code was ${response?.code}")
        }
    }

    fun fetchImage(url: String?): Bitmap? {
        return try {
            val inputStream: InputStream = URL(url).content as InputStream
            val d = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            d
        } catch (e: Exception) {
            null
        }
    }

}