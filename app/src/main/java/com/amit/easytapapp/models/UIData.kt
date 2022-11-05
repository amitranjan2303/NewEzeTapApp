package com.amit.easytapapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UIData(
    var uitype: String? = null,
    var value: String? = null,
    var key: String? = null,
    var hint: String? = null
): Parcelable {

//    companion object{
//        @JvmField final val CREATOR:Parcelable.Creator<UIData> =object : Parcelable.Creator<UIData>{
//          override fun createFromParcel(source: Parcel?): UIData {
//             return UIData(source)
//          }
//
//          override fun newArray(size: Int): Array<UIData?> {
//             return arrayOfNulls(size)
//          }
//      }
//    }
//
//    constructor(parcel: Parcel?) : this(
//        parcel?.readString(),
//        parcel?.readString(),
//        parcel?.readString(),
//        parcel?.readString()
//    ) {
//    }
//
//    override fun describeContents(): Int {
//       return 0
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeString(uitype)
//        dest.writeString(value)
//        dest.writeString(key)
//        dest.writeString(hint)
//    }

//     companion object CREATOR : Parcelable.Creator<UIData> {
//        override fun createFromParcel(parcel: Parcel): UIData {
//            return UIData(parcel)
//        }
//
//        override fun newArray(size: Int): Array<UIData?> {
//            return arrayOfNulls(size)
//        }
//    }
}