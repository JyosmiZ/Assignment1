package com.example.myapplication1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Permissions(
    val admin: Boolean?,
    val maintain: Boolean?,
    val pull: Boolean?,
    val push: Boolean?,
    val triage: Boolean?
) :Parcelable