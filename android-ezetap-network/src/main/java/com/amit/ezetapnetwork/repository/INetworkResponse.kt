package com.amit.ezetapnetwork.repository

interface INetworkResponse {
    fun onResponseSuccess(response:String)
    fun onResponseError(e:Exception)
}