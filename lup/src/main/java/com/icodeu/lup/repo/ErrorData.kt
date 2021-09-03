package com.icodeu.lup.repo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ErrorData(val title: String="Error...", val stacktrace: String="") : Parcelable {

}