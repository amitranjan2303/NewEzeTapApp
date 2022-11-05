package com.amit.easytapapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UIData(
    @SerializedName("uitype")
    var uiType: String? = null,
    var value: String? = null,
    var key: String? = null,
    var hint: String? = null
): Parcelable