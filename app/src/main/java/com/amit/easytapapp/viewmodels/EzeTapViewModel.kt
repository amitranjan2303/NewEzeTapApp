package com.amit.easytapapp.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amit.easytapapp.models.EzeTapModel
import com.amit.ezetapnetwork.repository.EzeTapNetworkRepository
import com.amit.ezetapnetwork.repository.INetworkResponse
import com.google.gson.Gson

class EzeTapViewModel : ViewModel() {

    private var ezeTapNetworkRepository: EzeTapNetworkRepository = EzeTapNetworkRepository()
    private var responseLiveData: MutableLiveData<EzeTapModel> = MutableLiveData<EzeTapModel>()
    private var responseBitmapLiveData: MutableLiveData<Bitmap> = MutableLiveData<Bitmap>()
    private var errorLiveData: MutableLiveData<Exception> = MutableLiveData<Exception>()

    init {
        ezeTapNetworkRepository.addNetworkResponseCallback(getNetworkResponseCallBack())
    }


    fun getNetworkResponseLiveData(): LiveData<EzeTapModel> {
        return responseLiveData
    }

    fun getResponseBitmapLiveData(): LiveData<Bitmap> {
        return responseBitmapLiveData
    }

    fun getNetworkErrorLiveData(): LiveData<Exception> {
        return errorLiveData
    }

    fun getNetworkResponse() {
        ezeTapNetworkRepository.fetchCustomUI("https://demo.ezetap.com/mobileapps/android_assignment.json")
    }

    fun fetchImage(url: String?) {
        ezeTapNetworkRepository.fetchImage(url)
    }

    override fun onCleared() {
        super.onCleared()
        ezeTapNetworkRepository.removeNetworkResponseCallback(getNetworkResponseCallBack())
    }

    private fun getNetworkResponseCallBack(): INetworkResponse {
        val networkCallback = object : INetworkResponse {

            override fun onResponseSuccess(response: Any?) {
                if (response is String) {
                    val gson = Gson()
                    val ezeTapModel: EzeTapModel =
                        gson.fromJson(response, EzeTapModel::class.java)
                    responseLiveData.value = ezeTapModel
                    ezeTapModel.logoUrl?.let { imageUrl ->
                        fetchImage(imageUrl)
                    }
                } else if (response is Bitmap) {
                    responseBitmapLiveData.value = response
                }
            }

            override fun onResponseError(e: Exception) {
                errorLiveData.value = e
            }

        }
        return networkCallback
    }

}