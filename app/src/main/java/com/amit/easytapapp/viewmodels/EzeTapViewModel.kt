package com.amit.easytapapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amit.easytapapp.models.EzeTapModel
import com.amit.ezetapnetwork.repository.EzeTapNetworkRepository
import com.amit.ezetapnetwork.repository.INetworkResponse
import com.google.gson.Gson
import java.lang.reflect.Executable

class EzeTapViewModel : ViewModel() {

    private var ezeTapNetworkRepository: EzeTapNetworkRepository
    private var responseLiveData: MutableLiveData<EzeTapModel>
    private var errorLiveData: MutableLiveData<Exception>

    init {
        responseLiveData = MutableLiveData<EzeTapModel>()
        errorLiveData = MutableLiveData<Exception>()
        ezeTapNetworkRepository = EzeTapNetworkRepository()
        ezeTapNetworkRepository.addNetworkResponseCallback(getNetworkResponseCallBack())
    }


    fun getNetworkResponseLiveData(): LiveData<EzeTapModel> {
        return responseLiveData
    }

    fun getNetworkErrorLiveData(): LiveData<Exception> {
        return errorLiveData
    }

    fun getNetworkResponse() {
        ezeTapNetworkRepository.ezeTapNetworkRequestCall()
    }

    override fun onCleared() {
        super.onCleared()
        ezeTapNetworkRepository.removeNetworkResponseCallback(getNetworkResponseCallBack())
    }

    private fun getNetworkResponseCallBack(): INetworkResponse {
        val networkCallback = object : INetworkResponse {

            override fun onResponseSuccess(response: String) {
                val gson = Gson()
                val ezeTapModel: EzeTapModel = gson.fromJson(response, EzeTapModel::class.java)
                responseLiveData.value = ezeTapModel
            }

            override fun onResponseError(e: Exception) {
                errorLiveData.value = e
            }

        }
        return networkCallback
    }

}