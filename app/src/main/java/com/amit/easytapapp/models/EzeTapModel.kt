package com.amit.easytapapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class EzeTapModel(
    @SerializedName("logo-url")
    var logoUrl: String? = null,
    @SerializedName("heading-text")
    var headingText: String? = null,
    var uidata: ArrayList<UIData>? = null
):Parcelable {

}