package com.amit.ezetapnetwork.repository

import android.os.Handler
import android.os.Looper
import com.amit.ezetapnetwork.network.EzeTapRemoteApi
import java.util.*
import java.util.concurrent.Executors

class EzeTapNetworkRepository {

    private var ezeTapRemoteApi = EzeTapRemoteApi()
    private val callBackHashSet: HashSet<INetworkResponse> = HashSet<INetworkResponse>()
    private val handler:Handler= Handler(Looper.getMainLooper())

    fun fetchCustomUI(url:String?) {
        Executors.newSingleThreadExecutor().execute {
            try {
                val responseBody = ezeTapRemoteApi.fetchCustomUI(url)
                invokeSuccessOrFailCallBack(1, responseBody)
            } catch (e: Exception) {
                invokeSuccessOrFailCallBack(2, e)
            }
        }
    }
    fun fetchImage(url:String?) {
        Executors.newSingleThreadExecutor().execute {
            try {
                val responseBody = ezeTapRemoteApi.fetchImage(url)
                if (responseBody != null) {
                    invokeSuccessOrFailCallBack(1, responseBody)
                } else {
                    invokeSuccessOrFailCallBack(2, Exception("Response is Null"))
                }
            } catch (e: Exception) {
                invokeSuccessOrFailCallBack(2, e)
            }
        }
    }

    fun addNetworkResponseCallback(networkResponseCallBack: INetworkResponse?) {
        networkResponseCallBack?.let {
            callBackHashSet.add(networkResponseCallBack)
        }
    }

    fun removeNetworkResponseCallback(networkResponseCallBack: INetworkResponse?) {
        networkResponseCallBack?.let {
            callBackHashSet.remove(networkResponseCallBack)
        }
    }

    private fun invokeSuccessOrFailCallBack(resultCode: Int?, response: Any) {
        handler.post {
            for (callBack in callBackHashSet) {
                when (resultCode) {
                    1 -> {
                        callBack.onResponseSuccess(response)
                    }
                    2 -> {
                        callBack.onResponseError(response as Exception)
                    }
                }
            }
        }
    }
}