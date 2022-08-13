package com.example.myapplication1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class License(
    val key: String?,
    val name: String?,
    val node_id: String?,
    val spdx_id: String?,
    val url: String?
) : Parcelable