package com.amit.ezetapnetwork.repository

interface INetworkResponse {
    fun onResponseSuccess(response:Any?)
    fun onResponseError(e:Exception)
}